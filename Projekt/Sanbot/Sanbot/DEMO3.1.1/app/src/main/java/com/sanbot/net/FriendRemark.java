package com.sanbot.net;

/**
 * Created by QH on 2017/1/7.
 */

public class FriendRemark {
    private int uid;

    private byte[] data;

    private String remark;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRemark() {
        if (this.remark != null) {
            return this.remark;
        }

        if (data == null) {
            return null;
        }

         return new String(data);
    }

    public byte[] getData() {
        return data;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
