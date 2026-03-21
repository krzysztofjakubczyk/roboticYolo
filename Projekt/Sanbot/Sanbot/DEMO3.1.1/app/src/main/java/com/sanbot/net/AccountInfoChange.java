package com.sanbot.net;

/**
 * Created by admin on 2017/9/18.
 */

public class AccountInfoChange {
    public static final int ALIAS_TYPE = 4;
    public static final int SEX_TYPE = 5;
    public static final int URL_TYPE = 6;

    private int type;

    private byte[] data;

    private String value;

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
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
