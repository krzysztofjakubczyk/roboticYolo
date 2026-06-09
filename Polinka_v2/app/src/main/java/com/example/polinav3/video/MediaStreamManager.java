package com.example.polinav3.video;

import com.sanbot.opensdk.beans.OperationResult;
import com.sanbot.opensdk.function.beans.StreamOption;
import com.sanbot.opensdk.function.unit.HDCameraManager;
import com.sanbot.opensdk.function.unit.interfaces.media.MediaStreamListener;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import android.support.annotation.NonNull;

public class MediaStreamManager {

    private List<Integer> handleList = new ArrayList<>();
    private HDCameraManager hdCameraManager;
    private VisionMediaDecoder mediaDecoder;
    private static final String TAG = "MediaStreamManager";
    private int mWidth, mHeight;

    public MediaStreamManager(HDCameraManager hdCameraManager, VisionMediaDecoder mediaDecoder) {
        this.hdCameraManager = hdCameraManager;
        this.mediaDecoder = mediaDecoder;
        this.handleList = new ArrayList<>();
        setupMediaListener();
    }

    public void openStream(Object unusedSurface) {
        //Set parameters and open the media stream for video
        StreamOption streamOption = new StreamOption();
        streamOption.setChannel(StreamOption.MAIN_STREAM);
        streamOption.setDecodType(StreamOption.HARDWARE_DECODE);
        streamOption.setJustIframe(false);
        OperationResult operationResult = hdCameraManager.openStream(streamOption);
        Log.i(TAG, "surfaceCreated: operationResult=" + operationResult.getResult());
        int result = Integer.valueOf(operationResult.getResult());
        if (result != -1) {
            if (handleList == null) {
                handleList = new ArrayList<>();
            }
            handleList.add(result);
        }
    }

    public void changeSurface(Object unusedSurface, int format, int width, int height) {

    }

    public void closeStream(Object unusedSurface) {
        Log.i(TAG, "surfaceDestroyed: ");
        //Close media stream
        if (handleList != null) {
            if (handleList.size() > 0) {
                for (int handle : handleList) {
                    Log.i(TAG, "surfaceDestroyed: " + hdCameraManager.closeStream(handle));
                }
            }
        }
        handleList = null;
        mediaDecoder.stopDecoding();
    }

    private void setupMediaListener() {
        hdCameraManager.setMediaListener(new MediaStreamListener() {
            @Override
            public void getVideoStream(int handle, byte[] bytes, int width, int height) {
                processVideoStream(handle, bytes, width, height);
            }

            @Override
            public void getAudioStream(int handle, @NonNull byte[] bytes) {
            }
        });
    }

    private void processVideoStream(int handle, byte[] bytes, int width, int height) {
        if (width != mWidth || height != mHeight) {
            mediaDecoder.onCreateCodec(width, height);
            mWidth = width;
            mHeight = height;
        }
        mediaDecoder.drawVideoSample(ByteBuffer.wrap(bytes));
        Log.i(TAG, "getVideoStream: Video data:" + bytes.length);
    }
}
