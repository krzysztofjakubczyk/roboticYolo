package com.sanbot.demo.manager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sanbot.net.NetApi;

import org.reactivestreams.Subscription;

import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 机器人摄像管理
 * @author youngbin
 */
public class VideoManager implements NetApi.ShowVideoListener {

    private static final String TAG = "VideoManager";

    private SurfaceView mSurfaceView;
    private boolean mPlaying;
    private Rect mRect;
    private Bitmap mBitmap = null;

    private BlockingQueue<byte[]> mVideoList = new ArrayBlockingQueue<>(10);


    public VideoManager(SurfaceView surfaceView) {
        mSurfaceView = surfaceView;
        NetApi.getInstance().setVideoListener(this);
        mPlaying = false;
        mRect = new Rect();
    }


    @Override
    public void showVideo(int handle, byte[] data, int len, int width, int height, int sec, int type) {

        if (width > 0 && height > 0 && data != null && data.length > 0) {

            if (mBitmap == null || mBitmap.isRecycled()) {
                mBitmap = Bitmap.createBitmap(width,
                        height, Bitmap.Config.RGB_565);
            }
            mVideoList.add(data);
        }
    }


    public void openVideo() {
        mPlaying = true;
        Flowable.empty().doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                while (mPlaying) {
                    byte[] buf = mVideoList.take();
                    if(mBitmap != null && !mBitmap.isRecycled()){
                        mBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(buf));
                    }
                    updateVideo(mBitmap);
                }
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void closeVideo() {
        mPlaying = false;
        if(mBitmap != null && !mBitmap.isRecycled()){
            mBitmap.recycle();
        }
    }


    private void updateVideo(Bitmap bitmap) {
        Canvas canvas = mSurfaceView.getHolder().lockCanvas();
        if (canvas != null) {
            mRect.set(0, 0, mSurfaceView.getWidth(), mSurfaceView.getHeight());
            Log.i(TAG, "updateVideo: "+mRect.toString());
            canvas.drawBitmap(bitmap, null, mRect, null);
            SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
            if (surfaceHolder != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }


}
