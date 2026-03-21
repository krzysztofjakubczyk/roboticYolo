package com.sanbot.net;

/**
 * Created by admin on 2017/4/21.
 */

public class VideoHandle {
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

    @Override
    public String toString() {
        return "VideoHandle{" +
                "handle=" + handle +
                ", reasonCode=" + reasonCode +
                ", type=" + type +
                '}';
    }
}
