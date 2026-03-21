package com.sanbot.net;

/**
 * Created by admin on 2017/12/27.
 */

public class UpdateDeviceNote {
    public static final int TYPE_DEVICE = 1;

    public static final int TYPE_FACESERVER = 2;

    private int opt_uid;

    private int companyId;

    private int type;

    private int uid;

    private String name;

    private byte[] nameData;

    public int getOpt_uid() {
        return opt_uid;
    }

    public void setOpt_uid(int opt_uid) {
        this.opt_uid = opt_uid;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
