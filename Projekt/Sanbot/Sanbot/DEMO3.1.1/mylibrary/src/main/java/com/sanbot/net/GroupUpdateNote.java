package com.sanbot.net;

/**
 * Created by admin on 2017/4/6.
 */

public class GroupUpdateNote {
    private int groupId;

    private int operUid;

    private int type;

    private String value;

    private byte[] data;

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

    public int getOperUid() {
        return operUid;
    }

    public void setOperUid(int operUid) {
        this.operUid = operUid;
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
