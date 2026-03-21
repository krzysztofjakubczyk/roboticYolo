package com.sanbot.net;

/**
 * Created by admin on 2017/12/25.
 */

public class SendMessageResponse {
    private long seq;

    private int timestamp;

    private int usec;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getUsec() {
        return usec;
    }

    public void setUsec(int usec) {
        this.usec = usec;
    }
}
