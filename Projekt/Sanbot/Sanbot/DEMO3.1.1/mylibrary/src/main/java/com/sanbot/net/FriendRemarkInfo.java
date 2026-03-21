package com.sanbot.net;

/**
 * Created by QHPC on 2016/4/19.
 * 好友备注信息
 */
public class FriendRemarkInfo {
    private int uid;

    private String remark;

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    /*public FriendRemarkInfo(int uid, String remark)
    {
        this.uid      = uid;
        this.remark   = remark;
    }*/

    @Override
    public String toString() {
        return "FriendRemarkInfo{" +
                "uid=" + uid +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRemark() {
        if (remark == null && data != null) {
            remark = new String(data);
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
