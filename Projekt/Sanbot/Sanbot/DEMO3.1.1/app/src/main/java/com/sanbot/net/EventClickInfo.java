package com.sanbot.net;

/**
 * 点击事件类
 * Created by QHPC on 2016/7/20.
 */
public class EventClickInfo {
    private int page = 0;

    private int button = 0;

    private int timestamp = 0;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
