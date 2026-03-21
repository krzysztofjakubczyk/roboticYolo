package com.sanbot.net;

/**
 * Created by admin on 2017/10/30.
 */

public class CompanyRobot {
    private int devUid;

    private int version;

    private String name;

    private byte[] nameData;

    public byte[] getNameData() {
        return nameData;
    }

    public void setNameData(byte[] nameData) {
        this.nameData = nameData;
    }

    public int getDevUid() {
        return devUid;
    }

    public void setDevUid(int devUid) {
        this.devUid = devUid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        if (nameData != null) {
            name = new String(nameData);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
