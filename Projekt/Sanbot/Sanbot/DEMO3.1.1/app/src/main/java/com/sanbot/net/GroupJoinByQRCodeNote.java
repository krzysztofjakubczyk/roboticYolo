package com.sanbot.net;

/**
 * Created by admin on 2017/4/6.
 */

public class GroupJoinByQRCodeNote {
    private int groupId;

    private int uid;

    private String remarks;

    private byte[] data;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRemarks() {
        if (data != null) {
            remarks = new String(data);
        }
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
