package com.sanbot.net;

/**
 * Created by admin on 2017/9/18.
 */

public class FriendInfoChange {
    public static final int REMARKS_TYPE = 4;

    private int uid;

    private int type;

    private byte[] data;

    private String value;

    private int version;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
