package com.example.polinav3;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.polinav3.R;
import com.example.polinav3.vision.YoloTfliteDetector;
import com.example.polinav3.video.MediaStreamManager;
import com.example.polinav3.video.VisionMediaDecoder;
import com.sanbot.opensdk.base.TopBaseActivity;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.function.beans.SpeakOption;
import com.sanbot.opensdk.function.unit.HDCameraManager;
import com.sanbot.opensdk.function.unit.SpeechManager;
import com.sanbot.opensdk.function.unit.WheelMotionManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends TopBaseActivity {

    private static final long DETECTION_INTERVAL_MS = 500;
    private static final long OBSTACLE_SPEAK_COOLDOWN_MS = 6000;
    private static final float CONFIDENCE_THRESHOLD = 0.50f;
    private static final float TOO_CLOSE_HEIGHT_RATIO = 0.50f;

    private HDCameraManager hdCameraManager;
    private SpeechManager speechManager;
    private WheelMotionManager wheelMotionManager;
    private MediaStreamManager mediaStreamManager;
    private VisionMediaDecoder mediaDecoder;
    private YoloTfliteDetector detector;

    private ImageView cameraPreview;
    private TextView statusText;
    private TextView logText;
    private ScrollView logScroll;

    private final ExecutorService detectionExecutor = Executors.newSingleThreadExecutor();
    private final AtomicBoolean detectionRunning = new AtomicBoolean(false);
    private long lastDetectionTime;
    private long lastObstacleSpeakTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        register(MainActivity.class);
        super.setContentView(R.layout.activity_main);

        cameraPreview = (ImageView) findViewById(R.id.cameraPreview);
        statusText = (TextView) findViewById(R.id.statusText);
        logText = (TextView) findViewById(R.id.logText);
        logScroll = (ScrollView) findViewById(R.id.logScroll);

        appendLog("Aplikacja uruchomiona. Czekam na serwis robota.");
        loadDetector();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detectionExecutor.shutdownNow();
        if (mediaStreamManager != null) {
            mediaStreamManager.closeStream(null);
        }
        if (detector != null) {
            detector.close();
        }
    }

    @Override
    protected void onMainServiceConnected() {
        hdCameraManager = (HDCameraManager) getUnitManager(FuncConstant.HDCAMERA_MANAGER);
        speechManager = (SpeechManager) getUnitManager(FuncConstant.SPEECH_MANAGER);
        wheelMotionManager = (WheelMotionManager) getUnitManager(FuncConstant.WHEELMOTION_MANAGER);

        mediaDecoder = new VisionMediaDecoder(this::onCameraFrame);
        mediaStreamManager = new MediaStreamManager(hdCameraManager, mediaDecoder);

        appendLog("Polaczono z serwisem robota.");
        setStatus(getString(R.string.status_camera_ready));
        mediaStreamManager.openStream(null);
    }

    private void loadDetector() {
        try {
            detector = YoloTfliteDetector.create(getAssets(), "model.tflite", "labels.txt");
            appendLog("Zaladowano model TFLite: model.tflite.");
        } catch (IOException | IllegalArgumentException e) {
            appendLog("Brak aktywnego modelu TFLite. Dodaj app/src/main/assets/model.tflite.");
            appendLog("Szczegoly: " + e.getMessage());
        }
    }

    private void onCameraFrame(Bitmap frame) {
        runOnUiThread(() -> cameraPreview.setImageBitmap(frame));

        if (detector == null) {
            return;
        }

        long now = System.currentTimeMillis();
        if (now - lastDetectionTime < DETECTION_INTERVAL_MS) {
            return;
        }
        if (!detectionRunning.compareAndSet(false, true)) {
            return;
        }

        lastDetectionTime = now;
        Bitmap inferenceFrame = frame.copy(Bitmap.Config.ARGB_8888, false);
        detectionExecutor.execute(() -> {
            try {
                List<YoloTfliteDetector.Detection> detections = detector.detect(inferenceFrame, CONFIDENCE_THRESHOLD);
                handleDetections(detections);
            } catch (RuntimeException e) {
                appendLog("Blad detekcji: " + e.getMessage());
            } finally {
                inferenceFrame.recycle();
                detectionRunning.set(false);
            }
        });
    }

    private void handleDetections(List<YoloTfliteDetector.Detection> detections) {
        if (detections.isEmpty()) {
            setStatus("Brak wykryc");
            return;
        }

        YoloTfliteDetector.Detection closest = detections.get(0);
        for (YoloTfliteDetector.Detection detection : detections) {
            if (detection.getHeightRatio() > closest.getHeightRatio()) {
                closest = detection;
            }
        }

        String message = closest.getLabel()
                + " conf=" + String.format("%.2f", closest.getConfidence())
                + " ratio=" + String.format("%.2f", closest.getHeightRatio());
        appendLog("YOLO: " + message);

        if (closest.getHeightRatio() >= TOO_CLOSE_HEIGHT_RATIO) {
            onVisualObstacleDetected(closest);
        } else {
            setStatus("Wykryto: " + closest.getLabel());
        }
    }

    private void onVisualObstacleDetected(YoloTfliteDetector.Detection detection) {
        setStatus(getString(R.string.status_obstacle));
        appendLog("Przeszkoda z obrazu: " + detection.getLabel() + ".");
        speakObstacleWarning();

        // Gdy chcesz automatycznie hamowac robota na podstawie obrazu, odkomentuj:
        // if (wheelMotionManager != null) {
        //     wheelMotionManager.doBrakeMotion();
        // }
    }

    private void speakObstacleWarning() {
        if (speechManager == null) {
            return;
        }

        long now = System.currentTimeMillis();
        if (now - lastObstacleSpeakTime < OBSTACLE_SPEAK_COOLDOWN_MS) {
            return;
        }
        lastObstacleSpeakTime = now;

        SpeakOption speakOption = new SpeakOption();
        speakOption.setLanguageType(SpeakOption.LAG_POLISH);
        speechManager.startSpeak("Uwaga, przeszkoda.", speakOption);
    }

    private void setStatus(String text) {
        runOnUiThread(() -> statusText.setText(text));
    }

    private void appendLog(String message) {
        runOnUiThread(() -> {
            String previous = logText.getText().toString();
            String next = previous.isEmpty() ? message : previous + "\n" + message;
            logText.setText(next);
            logScroll.post(() -> logScroll.fullScroll(ScrollView.FOCUS_DOWN));
        });
    }
}
