package com.sanbot.net;

/**
 * Created by admin on 2017/4/21.
 */

public class VideoHandle {
    private int h264Data = 0;

    private long handle;

    private int reasonCode;

    private int type;

    public long getHandle() {
        return handle;
    }

    public void setHandle(long handle) {
        this.handle = handle;
    }

    public int getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getH264Data() {
        return h264Data;
    }

    public void setH264Data(int h264Data) {
        this.h264Data = h264Data;
    }

    @Override
    public String toString() {
        return "VideoHandle{" +
                "handle=" + handle +
                ", reasonCode=" + reasonCode +
                ", type=" + type +
                '}';
    }
}
