package com.example.polinav3.video;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VisionMediaDecoder {
    public interface FrameListener {
        void onFrame(Bitmap frame);
    }

    private static final String TAG = "VisionMediaDecoder";
    private static final long DECODE_TIMEOUT_US = 10000;

    private final Lock lock = new ReentrantLock();
    private final FrameListener frameListener;

    private MediaCodec videoDecoder;
    private MediaCodec.BufferInfo videoBufferInfo = new MediaCodec.BufferInfo();
    private ImageReader imageReader;
    private HandlerThread imageThread;
    private Handler imageHandler;
    private ByteBuffer[] videoInputBuffers;
    private String videoMimeType = "video/avc";
    private int videoWidth;
    private int videoHeight;

    public VisionMediaDecoder(FrameListener frameListener) {
        this.frameListener = frameListener;
    }

    public void drawVideoSample(ByteBuffer sampleData) {
        try {
            lock.lock();
            if (videoDecoder == null) {
                return;
            }

            int inputIndex = videoDecoder.dequeueInputBuffer(DECODE_TIMEOUT_US);
            if (inputIndex >= 0) {
                ByteBuffer buffer = videoInputBuffers[inputIndex];
                int sampleSize = sampleData.limit();
                buffer.clear();
                buffer.put(sampleData);
                buffer.flip();
                videoDecoder.queueInputBuffer(inputIndex, 0, sampleSize, 0, 0);
            }

            int outputIndex = videoDecoder.dequeueOutputBuffer(videoBufferInfo, DECODE_TIMEOUT_US);
            if (outputIndex >= 0) {
                videoDecoder.releaseOutputBuffer(outputIndex, true);
            } else {
                onDecodingInfo(outputIndex);
            }
        } catch (Exception e) {
            Log.e(TAG, "Decoder error", e);
            stopDecoding();
        } finally {
            lock.unlock();
        }
    }

    public void onCreateCodec(int width, int height) {
        videoWidth = width;
        videoHeight = height;
        stopDecoding();
        startDecoding();
    }

    public boolean startDecoding() {
        try {
            lock.lock();
            if (videoDecoder != null || videoInputBuffers != null) {
                return false;
            }

            imageThread = new HandlerThread("VisionImageReader");
            imageThread.start();
            imageHandler = new Handler(imageThread.getLooper());
            imageReader = ImageReader.newInstance(videoWidth, videoHeight, ImageFormat.YUV_420_888, 2);
            imageReader.setOnImageAvailableListener(this::onImageAvailable, imageHandler);

            MediaFormat format = MediaFormat.createVideoFormat(videoMimeType, videoWidth, videoHeight);
            format.setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC);
            format.setInteger(MediaFormat.KEY_SAMPLE_RATE, 44100);
            format.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 1);
            format.setInteger(MediaFormat.KEY_BIT_RATE, 64000);

            videoDecoder = MediaCodec.createDecoderByType(videoMimeType);
            videoDecoder.configure(format, imageReader.getSurface(), null, 0);
            videoDecoder.start();
            videoInputBuffers = videoDecoder.getInputBuffers();
            Log.i(TAG, "Decoder started: " + videoWidth + "x" + videoHeight);
            return true;
        } catch (IOException | IllegalStateException e) {
            Log.e(TAG, "Cannot start decoder", e);
            stopDecoding();
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void stopDecoding() {
        try {
            lock.lock();
            if (videoDecoder != null) {
                try {
                    videoDecoder.stop();
                } catch (IllegalStateException ignored) {
                }
                videoDecoder.release();
                videoDecoder = null;
            }
            videoInputBuffers = null;

            if (imageReader != null) {
                imageReader.close();
                imageReader = null;
            }
            if (imageThread != null) {
                imageThread.quitSafely();
                imageThread = null;
                imageHandler = null;
            }
        } finally {
            lock.unlock();
        }
    }

    private void onImageAvailable(ImageReader reader) {
        Image image = null;
        try {
            image = reader.acquireLatestImage();
            if (image == null || frameListener == null) {
                return;
            }

            Bitmap bitmap = imageToBitmap(image);
            if (bitmap != null) {
                frameListener.onFrame(bitmap);
            }
        } catch (RuntimeException e) {
            Log.e(TAG, "Cannot read decoded frame", e);
        } finally {
            if (image != null) {
                image.close();
            }
        }
    }

    private Bitmap imageToBitmap(Image image) {
        byte[] nv21 = yuv420ToNv21(image);
        YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, image.getWidth(), image.getHeight(), null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, image.getWidth(), image.getHeight()), 80, out);
        byte[] jpegBytes = out.toByteArray();
        return BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.length);
    }

    private byte[] yuv420ToNv21(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int ySize = width * height;
        byte[] nv21 = new byte[ySize + ySize / 2];

        copyPlane(image.getPlanes()[0], width, height, nv21, 0, 1);
        interleaveChromaPlanes(image.getPlanes()[1], image.getPlanes()[2], width, height, nv21, ySize);
        return nv21;
    }

    private void copyPlane(Image.Plane plane, int width, int height, byte[] output, int offset, int pixelStrideOut) {
        ByteBuffer buffer = plane.getBuffer();
        int rowStride = plane.getRowStride();
        int pixelStride = plane.getPixelStride();
        byte[] row = new byte[rowStride];
        int outputIndex = offset;

        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            int length = Math.min(rowStride, buffer.remaining());
            buffer.get(row, 0, length);
            for (int col = 0; col < width; col++) {
                output[outputIndex] = row[col * pixelStride];
                outputIndex += pixelStrideOut;
            }
        }
    }

    private void interleaveChromaPlanes(
            Image.Plane uPlane,
            Image.Plane vPlane,
            int width,
            int height,
            byte[] output,
            int offset
    ) {
        ByteBuffer uBuffer = uPlane.getBuffer();
        ByteBuffer vBuffer = vPlane.getBuffer();
        int chromaWidth = width / 2;
        int chromaHeight = height / 2;
        int uRowStride = uPlane.getRowStride();
        int vRowStride = vPlane.getRowStride();
        int uPixelStride = uPlane.getPixelStride();
        int vPixelStride = vPlane.getPixelStride();
        byte[] uRow = new byte[uRowStride];
        byte[] vRow = new byte[vRowStride];
        int outputIndex = offset;

        for (int rowIndex = 0; rowIndex < chromaHeight; rowIndex++) {
            int uLength = Math.min(uRowStride, uBuffer.remaining());
            int vLength = Math.min(vRowStride, vBuffer.remaining());
            uBuffer.get(uRow, 0, uLength);
            vBuffer.get(vRow, 0, vLength);

            for (int col = 0; col < chromaWidth; col++) {
                output[outputIndex++] = vRow[col * vPixelStride];
                output[outputIndex++] = uRow[col * uPixelStride];
            }
        }
    }

    private void onDecodingInfo(int index) {
        if (index == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED && videoDecoder != null) {
            Log.d(TAG, "New decoder format: " + videoDecoder.getOutputFormat());
        } else if (index == MediaCodec.INFO_TRY_AGAIN_LATER) {
            Log.d(TAG, "Decoder output timed out");
        }
    }
}
