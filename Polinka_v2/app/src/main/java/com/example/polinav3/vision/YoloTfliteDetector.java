package com.example.polinav3.vision;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YoloTfliteDetector implements AutoCloseable {

    private static final int DEFAULT_THREAD_COUNT = 4;

    private final Interpreter interpreter;
    private final List<String> labels;
    private final int inputWidth;
    private final int inputHeight;
    private final DataType inputType;
    private final int[] outputShape;

    public static YoloTfliteDetector create(
            AssetManager assetManager,
            String modelAssetName,
            String labelsAssetName
    ) throws IOException {
        MappedByteBuffer model = loadModel(assetManager, modelAssetName);
        List<String> labels = loadLabels(assetManager, labelsAssetName);
        return new YoloTfliteDetector(model, labels);
    }

    private YoloTfliteDetector(MappedByteBuffer model, List<String> labels) {
        Interpreter.Options options = new Interpreter.Options();
        options.setNumThreads(DEFAULT_THREAD_COUNT);
        interpreter = new Interpreter(model, options);

        Tensor inputTensor = interpreter.getInputTensor(0);
        int[] inputShape = inputTensor.shape();
        inputHeight = inputShape[1];
        inputWidth = inputShape[2];
        inputType = inputTensor.dataType();
        outputShape = interpreter.getOutputTensor(0).shape();
        this.labels = labels;
    }

    public List<Detection> detect(Bitmap source, float confidenceThreshold) {
        if (outputShape.length != 3) {
            throw new IllegalStateException("Unsupported YOLO output shape length: " + outputShape.length);
        }

        Bitmap resized = Bitmap.createBitmap(inputWidth, inputHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resized);
        canvas.drawBitmap(source, null, new android.graphics.Rect(0, 0, inputWidth, inputHeight), null);

        ByteBuffer input = createInputBuffer(resized);
        float[][][] output = new float[outputShape[0]][outputShape[1]][outputShape[2]];
        interpreter.run(input, output);
        resized.recycle();

        return parseOutput(output, confidenceThreshold);
    }

    @Override
    public void close() {
        interpreter.close();
    }

    private ByteBuffer createInputBuffer(Bitmap bitmap) {
        int bytesPerChannel = inputType == DataType.FLOAT32 ? 4 : 1;
        ByteBuffer buffer = ByteBuffer.allocateDirect(inputWidth * inputHeight * 3 * bytesPerChannel);
        buffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[inputWidth * inputHeight];
        bitmap.getPixels(pixels, 0, inputWidth, 0, 0, inputWidth, inputHeight);
        for (int pixel : pixels) {
            int r = (pixel >> 16) & 0xFF;
            int g = (pixel >> 8) & 0xFF;
            int b = pixel & 0xFF;

            if (inputType == DataType.FLOAT32) {
                buffer.putFloat(r / 255.0f);
                buffer.putFloat(g / 255.0f);
                buffer.putFloat(b / 255.0f);
            } else {
                buffer.put((byte) r);
                buffer.put((byte) g);
                buffer.put((byte) b);
            }
        }
        buffer.rewind();
        return buffer;
    }

    private List<Detection> parseOutput(float[][][] output, float confidenceThreshold) {
        int first = outputShape[1];
        int second = outputShape[2];

        if (second == 6 || second == 7) {
            return parseNmsOutput(output, confidenceThreshold);
        }
        if (first < second) {
            return parseTransposedYoloOutput(output, confidenceThreshold);
        }
        return parseYoloOutput(output, confidenceThreshold);
    }

    private List<Detection> parseYoloOutput(float[][][] output, float confidenceThreshold) {
        int predictionCount = outputShape[1];
        int channelCount = outputShape[2];
        List<Detection> detections = new ArrayList<>();

        for (int i = 0; i < predictionCount; i++) {
            Detection detection = readYoloPrediction(output[0][i], channelCount, confidenceThreshold);
            if (detection != null) {
                detections.add(detection);
            }
        }
        Collections.sort(detections, (a, b) -> Float.compare(b.confidence, a.confidence));
        return detections;
    }

    private List<Detection> parseTransposedYoloOutput(float[][][] output, float confidenceThreshold) {
        int channelCount = outputShape[1];
        int predictionCount = outputShape[2];
        List<Detection> detections = new ArrayList<>();

        for (int i = 0; i < predictionCount; i++) {
            float[] prediction = new float[channelCount];
            for (int channel = 0; channel < channelCount; channel++) {
                prediction[channel] = output[0][channel][i];
            }

            Detection detection = readYoloPrediction(prediction, channelCount, confidenceThreshold);
            if (detection != null) {
                detections.add(detection);
            }
        }
        Collections.sort(detections, (a, b) -> Float.compare(b.confidence, a.confidence));
        return detections;
    }

    private Detection readYoloPrediction(float[] prediction, int channelCount, float confidenceThreshold) {
        if (channelCount < 5) {
            return null;
        }

        float bestScore = 0.0f;
        int bestClass = -1;
        for (int channel = 4; channel < channelCount; channel++) {
            if (prediction[channel] > bestScore) {
                bestScore = prediction[channel];
                bestClass = channel - 4;
            }
        }
        if (bestScore < confidenceThreshold) {
            return null;
        }

        float cx = normalizeCoordinate(prediction[0]);
        float cy = normalizeCoordinate(prediction[1]);
        float width = normalizeSize(prediction[2]);
        float height = normalizeSize(prediction[3]);
        RectF box = new RectF(
                clamp(cx - width / 2),
                clamp(cy - height / 2),
                clamp(cx + width / 2),
                clamp(cy + height / 2)
        );

        return new Detection(labelFor(bestClass), bestScore, box);
    }

    private List<Detection> parseNmsOutput(float[][][] output, float confidenceThreshold) {
        int predictionCount = outputShape[1];
        List<Detection> detections = new ArrayList<>();

        for (int i = 0; i < predictionCount; i++) {
            float[] prediction = output[0][i];
            float score = prediction[4];
            if (score < confidenceThreshold) {
                continue;
            }

            int classIndex = Math.round(prediction[5]);
            RectF box = new RectF(
                    clamp(normalizeCoordinate(prediction[0])),
                    clamp(normalizeCoordinate(prediction[1])),
                    clamp(normalizeCoordinate(prediction[2])),
                    clamp(normalizeCoordinate(prediction[3]))
            );
            detections.add(new Detection(labelFor(classIndex), score, box));
        }
        Collections.sort(detections, (a, b) -> Float.compare(b.confidence, a.confidence));
        return detections;
    }

    private String labelFor(int classIndex) {
        if (classIndex >= 0 && classIndex < labels.size()) {
            return labels.get(classIndex);
        }
        return "class_" + classIndex;
    }

    private float normalizeCoordinate(float value) {
        if (value > 1.0f) {
            return value / inputWidth;
        }
        return value;
    }

    private float normalizeSize(float value) {
        if (value > 1.0f) {
            return value / inputHeight;
        }
        return value;
    }

    private float clamp(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    private static MappedByteBuffer loadModel(AssetManager assetManager, String assetName) throws IOException {
        try (AssetFileDescriptor fileDescriptor = assetManager.openFd(assetName);
             FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
             FileChannel fileChannel = inputStream.getChannel()) {
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        }
    }

    private static List<String> loadLabels(AssetManager assetManager, String assetName) {
        List<String> loadedLabels = new ArrayList<>();
        try (InputStream inputStream = assetManager.open(assetName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String label = line.trim();
                if (!label.isEmpty()) {
                    loadedLabels.add(label);
                }
            }
        } catch (IOException ignored) {
        }
        return loadedLabels;
    }

    public static class Detection {
        private final String label;
        private final float confidence;
        private final RectF box;

        Detection(String label, float confidence, RectF box) {
            this.label = label;
            this.confidence = confidence;
            this.box = box;
        }

        public String getLabel() {
            return label;
        }

        public float getConfidence() {
            return confidence;
        }

        public float getHeightRatio() {
            return box.height();
        }
    }
}
