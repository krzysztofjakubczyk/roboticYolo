package com.sanbot.net;

/**
 * Created by admin on 2017/8/1.
 */

public class ReceptionReply {
    private int seq;

    private int type;

    private String text;

    private byte[] data;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        if (data != null) {
            text = new String(data);
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReceptionReply{" +
                "seq=" + seq +
                ", type=" + type +
                ", text='" + text + '\'' +
                '}';
    }
}
