package com.sanbot.demo.app.start.main.chat.video;

import android.content.Context;

import com.sanbot.demo.LiveManager;
import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.entity.Message;
import com.sanbot.demo.manager.model.VideoImp;
import com.sanbot.demo.manager.model.biz.IVideo;
import com.sanbot.demo.util.DateUtil;

import org.reactivestreams.Subscription;

import java.util.Date;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/4/27.
 */

public class VideoPresenter extends BasePresenter {

    private static final String TAG = "VideoPresenter";

    private IVideoView mIVideoView;
    private IVideo mIVideo;

    private Message mMessage;
    private LiveManager mLiveManager;


    public VideoPresenter(Context context) {
        super(context);
    }

    public VideoPresenter(Context context, IVideoView iVideoView) {
        this(context);
        mIVideoView = iVideoView;
        mIVideo = new VideoImp(context);
        init();
    }

    private void init() {
        mLiveManager = new LiveManager(mContext, String.valueOf(mIVideo.getUid()), String.valueOf(mIVideoView.getToUid()), mIVideoView.getIP());
        mLiveManager.setLayout(mIVideoView.getLayout());

        mLiveManager.initCapture();
        mLiveManager.startCapture();

        mMessage = getMessage();
        long result = mIVideo.update(mMessage);
        mMessage.setId(result);
    }

    public void sendMsg(final int type) {
        final int toUid = mIVideoView.getToUid();
        byte[] data = mContext.getString(R.string.chat_video).getBytes();
        mIVideo.onSendMsg(toUid, data, data.length, type, getSeq());
    }

    public void startRender() {
        mLiveManager.initRender();
        mLiveManager.startRender();
    }

    public void saveMessage(String content) {
        if (mMessage != null) {
            mMessage.setContent(content);
            mIVideo.update(mMessage);
        }
    }

    private Message getMessage() {
        Date date = new Date();
        Message message = new Message();
        message.setType(mIVideoView.getType());
        message.setData(mIVideoView.getIP().getBytes());
        message.setDate(date.getTime());
        String dateText = DateUtil.getText(date);
        message.setDateText(dateText);
        message.setState(Message.STATE_SEND_SUCCESS);

        if (mIVideoView.getState() == VideoActivity.STATE_VIDEO_REQUEST) {
            message.setToId(mIVideoView.getToUid());
            message.setFromId(mIVideo.getUid());
        } else {
            message.setFromId(mIVideoView.getToUid());
            message.setToId(mIVideo.getUid());
        }
        message.setRead(true);
        return message;
    }

    public void switchView(){
        mLiveManager.switchView();
    }

    public void stop(){
        mDisposable.add(Flowable.empty().doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                mLiveManager.stop();
            }
        }).subscribeOn(Schedulers.newThread()).subscribe());
    }


    @Override
    public void clear() {
        super.clear();
        mDisposable.add(Flowable.empty().doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                mLiveManager.close();
            }
        }).subscribeOn(Schedulers.newThread()).subscribe());
    }
}
