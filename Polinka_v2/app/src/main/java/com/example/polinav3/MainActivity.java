package com.example.polinav3;

import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

import com.sanbot.opensdk.base.TopBaseActivity;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.function.unit.HDCameraManager;

import com.example.polinav3.video.MediaStreamManager;
import com.example.polinav3.video.VisionMediaDecoder;

public class MainActivity extends TopBaseActivity implements SurfaceHolder.Callback {

    private HDCameraManager hdCameraManager;
    private MediaStreamManager mediaStreamManager;
    private VisionMediaDecoder mediaDecoder;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        register(MainActivity.class);
        setContentView(R.layout.activity_main);

        // 1. Znajdź SurfaceView w layout_main.xml (upewnij się, że masz tam taki ID)
        surfaceView = findViewById(R.id.surfaceViewCamera);

        // 2. Dodaj Callback, aby wiedzieć, kiedy ekran jest gotowy do wyświetlania
        surfaceView.getHolder().addCallback(this);
    }

    @Override
    protected void onMainServiceConnected() {
        // 3. Zainicjuj menedżery po połączeniu z usługą robota
        hdCameraManager = (HDCameraManager) getUnitManager(FuncConstant.HDCAMERA_MANAGER);
        mediaDecoder = new VisionMediaDecoder();
        mediaStreamManager = new MediaStreamManager(hdCameraManager, mediaDecoder);
    }

    // --- Obsługa SurfaceHolder.Callback ---

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // 4. Gdy powierzchnia powstanie, otwórz strumień wideo
        if (mediaStreamManager != null) {
            mediaStreamManager.openStream(holder.getSurface());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // Opcjonalnie: obsługa zmiany orientacji/rozmiaru
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // 5. Zamknij strumień, gdy wychodzisz z aplikacji lub niszczysz widok
        if (mediaStreamManager != null) {
            mediaStreamManager.closeStream(holder.getSurface());
        }
    }
}