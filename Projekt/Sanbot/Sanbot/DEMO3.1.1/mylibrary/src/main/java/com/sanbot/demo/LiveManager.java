package com.sanbot.demo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.peergine.android.live.pgLibLive;
import com.peergine.android.live.pgLibLiveMode;

/**
 * Created by admin on 2017/5/5.
 */

public class LiveManager {

    private static final String TAG = "LiveManager";

    public static final int STATE_CAPTURE = 1;
    public static final int STATE_RENDER = 2;

    //采集
    private pgLibLive mCaptureLive = new pgLibLive();
    private SurfaceView mCaptureView;
    private String mCaptureId;

    //渲染
    private pgLibLive mRenderLive = new pgLibLive();
    private SurfaceView mRenderView;
    private String mRenderId;

    private FrameLayout mLayout;
    private Context mContext;
    private String mIP;
    private int mState;

    public LiveManager(Context context, String captureId, String renderId, String ip) {
        if (context == null || captureId == null || renderId == null || ip == null) {
            throw new NullPointerException("context=" + context + ",captureId=" + captureId + ",renderId=" + renderId + ",ip=" + ip);
        }
        Log.i(TAG, "LiveManager: captureId=" + captureId + ",renderId=" + renderId + ",ip=" + ip);
        mContext = context;
        mCaptureId = captureId;
        mRenderId = renderId;
        mIP = ip;
        mState = STATE_CAPTURE;
    }


    public void setLayout(FrameLayout layout) {
        mLayout = layout;
    }

    /**
     * 采集初始化
     * <p>
     * sVideoParam：[IN] 视频参数，格式为：(Code){3}(Mode){2}(Rate){40}(CameraNo){0}(Portrait){1}
     * (BitRate){400}(MaxStream){1}(Forward){0}
     * Code: 视频压缩编码类型：1为MJPEG、2为VP8、3为H264、4为H265。（仅采集端有效）
     * Mode: 视频图像的分辨率（尺寸），有效数值如下：（仅采集端有效）
     * 0: 80x60, 1: 160x120, 2: 320x240, 3: 640x480,
     * 4: 800x600, 5: 1024x768, 6: 176x144, 7: 352x288,
     * 8: 704x576, 9: 854x480, 10: 1280x720, 11: 1920x1080
     * Rate: 视频的帧间间隔（毫秒）。例如40毫秒的帧率为：1000/40 = 25 fps（仅采集端有效）
     * CameraNo: 摄像头编号，CameraInfo.facing的值，默认为前置（可选，仅采集端有效）
     * Portrait: 采集图像的方向。0为横屏，1为竖屏。（可选，仅采集端有效）
     * BitRate: 视频压缩后的码率。单位为 Kbps（仅采集端有效）
     * MaxStream: 允许最大发送的视频流数量，默认为2条。（可选，采集端和客户端都有效）
     * Forward: 是否启用服务器转发视频流。0为禁用，1为启用。（可选，仅采集端有效）
     * FilterDecode: 启用解码后的模糊滤镜。0为禁用，1为启用。（可选，仅播放端有效）
     * InputExternal: 启用视频输入回调接口。0为禁用，1为启用。（可选，仅采集端有效）
     * OutputExternal: 启用视频解码后输出回调接口。0为禁用，1为启用。（可选，仅播放端有效）
     * OutputExtCmp: 启用视频解码前输出回调接口。0为禁用，1为启用。（可选，仅播放端有效）
     */
    public void initCapture() {
        if (!mCaptureLive.Initialize(pgLibLiveMode.Capture, mCaptureId, "", mIP, "", 3,
                "(Code){3}(Mode){3}(Rate){66}(Portrait){1}(BitRate){600}(MaxStream){3}(Forward){0}", mContext)) {
            Log.i(TAG, "initCapture: 初始化失败");
            return;
        }
        Log.i(TAG, "initCapture: 采集初始化成功");
        mCaptureView = mCaptureLive.WndCreate(0, 0, 320, 240);
        if (mCaptureView == null || mLayout == null) {
            Log.i(TAG, "startCapture: captureView or captureLayout not null");
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayout.addView(mCaptureView, params);
        mCaptureView.setVisibility(View.VISIBLE);
    }

    /**
     * 渲染初始化
     */
    public void initRender() {
        if (!mRenderLive.Initialize(pgLibLiveMode.Render, mRenderId,
                "", mIP, "", 3, "(MaxStream){0}", mContext)) {
            Log.i(TAG, "initRender: 初始化失败");
            return;
        }
        Log.i(TAG, "initRender: 渲染初始化成功");
        mRenderView = mRenderLive.WndCreate(0, 0, 320, 240);
        if (mRenderView == null || mLayout == null) {
            Log.i(TAG, "initRender: renderView or renderLayout not null");
            return;
        }
        mLayout.addView(mRenderView);
        mRenderView.setVisibility(View.VISIBLE);
        switchView();
    }

    public void switchView() {
        if (mRenderView == null || mCaptureView == null) {
            return;
        }
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(320, 480);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayout.removeAllViews();


        if(mState == STATE_CAPTURE){
            mState = STATE_RENDER;
            mRenderView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            mRenderView.setZOrderOnTop(false);
            mRenderView.setZOrderMediaOverlay(false);
            mRenderView.setLayoutParams(params2);

            mLayout.addView(mRenderView);

            mCaptureView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            mCaptureView.setZOrderOnTop(true);
            mCaptureView.setZOrderMediaOverlay(true);
            mCaptureView.setLayoutParams(params1);
            mLayout.addView(mCaptureView);

        }else{
            mState = STATE_CAPTURE;
            mCaptureView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            mCaptureView.setZOrderOnTop(false);
            mCaptureView.setZOrderMediaOverlay(false);
            mCaptureView.setLayoutParams(params2);
            mLayout.addView(mCaptureView);

            mRenderView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            mRenderView.setZOrderOnTop(true);
            mRenderView.setZOrderMediaOverlay(true);
            mRenderView.setLayoutParams(params1);
            mLayout.addView(mRenderView);
        }
    }

    public void startCapture() {
        if (TextUtils.isEmpty(mCaptureId)) {
            Log.i(TAG, "startCapture: captureId not null");
            return;
        }
        Log.i(TAG, "startCapture: captureId=" + mCaptureId);
        mCaptureLive.Start(mCaptureId);
        mCaptureLive.VideoStart();
        mCaptureLive.AudioStart();
    }

    public void startRender() {
        if (TextUtils.isEmpty(mRenderId)) {
            Log.i(TAG, "startRender: renderId not null");
            return;
        }
        Log.i(TAG, "startRender: renderId=" + mRenderId);
        mRenderLive.Start(mRenderId);
        mRenderLive.VideoStart();
        mRenderLive.AudioStart();
    }

    public void stop() {
        mCaptureLive.VideoStop();
        mCaptureLive.AudioStop();
        mCaptureLive.Stop();

        mRenderLive.VideoStop();
        mRenderLive.AudioStop();
        mRenderLive.Stop();
    }

    public void close() {
        stop();
        if (mLayout != null) {
            mLayout.removeAllViews();
        }
    }

}
