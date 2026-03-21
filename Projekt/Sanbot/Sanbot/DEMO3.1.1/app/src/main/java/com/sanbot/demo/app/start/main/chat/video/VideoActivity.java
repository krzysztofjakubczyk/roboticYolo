package com.sanbot.demo.app.start.main.chat.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.demo.entity.Message;
import com.sanbot.demo.util.JsonUtil;
import com.sanbot.net.ChatMessage;
import com.sanbot.net.NetInfo;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/4/27.
 */

public class VideoActivity extends BaseActivity implements IVideoView {

    private static final String TAG = "VideoActivity";

    public static final int STATE_VIDEO_REQUEST = 1;
    public static final int STATE_VIDEO_RESPONSE = 2;

    @BindView(R.id.video_layout)
    FrameLayout mLayout;

    @BindView(R.id.video_text_tv)
    TextView mTextView;

    @BindView(R.id.video_reject_btn)
    Button mRejectBtn;

    @BindView(R.id.video_cancel_btn)
    Button mCancelBtn;

    @BindView(R.id.video_confirm_btn)
    Button mConfirmBtn;

    private VideoPresenter mPresenter;
    private int mToUid;
    private String mIP;
    private int mState;
    private int mType;

    public static void startActivity(Context context, int toUid, byte[] data, int state, int type) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("to_uid", toUid);
        intent.putExtra("state", state);
        intent.putExtra("data", data);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_SEND_CHAT_MSG_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_RECV_CHAT_MSG));
        LocalBroadcastManager.getInstance(this).registerReceiver(mVideoReceiver, filter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(getString(R.string.chat_video));

        Intent intent = getIntent();
        mToUid = intent.getIntExtra("to_uid", -1);
        mState = intent.getIntExtra("state", -1);
        mType = intent.getIntExtra("type", -1);
        byte[] data = intent.getByteArrayExtra("data");
        if (data != null) {
            mIP = JsonUtil.jsonP2PServerIP(new String(data));
        }

        mPresenter = new VideoPresenter(this, this);

        if (mState == STATE_VIDEO_REQUEST) {
            setText(getString(R.string.chat_request_video, mToUid));
            mRejectBtn.setVisibility(View.GONE);
            mCancelBtn.setVisibility(View.VISIBLE);
            mConfirmBtn.setVisibility(View.GONE);

        } else if (mState == STATE_VIDEO_RESPONSE) {
            setText(getString(R.string.chat_get_request_video, mToUid));
            mRejectBtn.setVisibility(View.VISIBLE);
            mCancelBtn.setVisibility(View.GONE);
            mConfirmBtn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @OnClick(R.id.video_confirm_btn)
    void onConfirm(View view) {
        mPresenter.sendMsg(NetInfo.CONTENT_TYPE_LIVE_AGREE);
        mPresenter.startRender();
        mTimer.schedule(mTimerTask, 1000, 1000);
        mRejectBtn.setVisibility(View.GONE);
        mCancelBtn.setVisibility(View.VISIBLE);
        mConfirmBtn.setVisibility(View.GONE);

    }

    @OnClick(R.id.video_cancel_btn)
    void onCancel(View view) {
        mPresenter.sendMsg(NetInfo.CONTENT_TYPE_LIVE_CANCEL);
        finish();
    }

    @OnClick(R.id.video_reject_btn)
    void onReject(View view) {
        mPresenter.sendMsg(NetInfo.CONTENT_TYPE_LIVE_REFUSE);
        setText(getString(R.string.chat_refuse));
        finish();
    }

    @OnClick(R.id.video_switch_btn)
    void onSwitch(View view){
        mPresenter.switchView();
    }

    private BroadcastReceiver mVideoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            JniResponse response = intent.getParcelableExtra("response");
            if (response == null)
                return;
            if (String.valueOf(NetInfo.QHC_CMD_SEND_CHAT_MSG_RSP).equals(action)) {


            } else if (String.valueOf(NetInfo.QHC_CMD_RECV_CHAT_MSG).equals(action) && response.getObj() instanceof ChatMessage) {
                Message message = intent.getParcelableExtra("message");
                switch (message.getType()) {
                    case NetInfo.CONTENT_TYPE_LIVE_AGREE:
                        //同意
                        showMsg(getString(R.string.chat_response_agree));
                        mPresenter.startRender();
                        mTimer.schedule(mTimerTask, 1000, 1000);
                        break;
                    case NetInfo.CONTENT_TYPE_LIVE_REFUSE:
                        //拒绝
                        showMsg(getString(R.string.chat_response_refuse));
                        setText(getString(R.string.chat_response_refuse));
                        finish();
                        break;
                    case NetInfo.CONTENT_TYPE_LIVE_CANCEL:
                        //取消
                        showMsg(getString(R.string.chat_response_cancel));
                        setText(getString(R.string.chat_response_cancel));
                        finish();
                        break;
                    case NetInfo.CONTENT_TYPE_LIVE_TALKING:
                        //正在通话中
                        showMsg(getString(R.string.chat_response_busy));
                        setText(getString(R.string.chat_response_busy));
                        finish();
                        break;
                }
            }
        }
    };


    @Override
    public int getState() {
        return mState;
    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public int getToUid() {
        return mToUid;
    }

    @Override
    public String getIP() {
        return mIP;
    }

    @Override
    public void setText(String text) {
        if (mSecond != 0)
            mTextView.setText(text);
    }

    @Override
    public FrameLayout getLayout() {
        return mLayout;
    }

    @Override
    protected void onDestroy() {
        mPresenter.saveMessage(mTextView.getText().toString());
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mVideoReceiver);
        mPresenter.clear();
        mTimer.cancel();
        super.onDestroy();
    }


    private volatile int mSecond = 0;
    private Timer mTimer = new Timer();

    TimerTask mTimerTask = new TimerTask() {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mSecond++;
                    String text = "";
                    if (mSecond < 60) {
                        text = String.format(Locale.getDefault(), "00:%02d", mSecond);
                    } else if (mSecond < 60 * 60) {
                        text = String.format(Locale.getDefault(), "%02d:%02d", mSecond / 60, mSecond % 60);
                    } else {
                        text = String.format(Locale.getDefault(), "%02d:%02d:%02d",
                                mSecond / 360, mSecond / 60, mSecond % 60);
                    }
                    setText(text);
                }
            });
        }
    };


}
