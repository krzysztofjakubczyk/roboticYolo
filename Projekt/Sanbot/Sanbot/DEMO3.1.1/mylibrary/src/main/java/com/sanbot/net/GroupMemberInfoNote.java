package com.sanbot.net;

/**
 * Created by admin on 2017/4/6.
 */

public class GroupMemberInfoNote {
    private int groupId;

    private int uid;

    private int type;//1-member_remarks

    private byte[] data;

    private String value;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        if (data != null) {
            value = new String(data);
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
