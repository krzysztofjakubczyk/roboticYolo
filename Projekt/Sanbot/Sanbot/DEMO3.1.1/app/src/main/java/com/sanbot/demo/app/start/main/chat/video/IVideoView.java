package com.sanbot.demo.app.start.main.chat.video;

import android.widget.FrameLayout;

import com.sanbot.demo.app.IBaseView;

/**
 * Created by admin on 2017/4/27.
 */

public interface IVideoView extends IBaseView {

    int getState();

    int getType();

    int getToUid();

    String getIP();

    void setText(String text);

    FrameLayout getLayout();


}
