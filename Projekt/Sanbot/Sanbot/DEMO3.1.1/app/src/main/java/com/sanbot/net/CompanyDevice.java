package com.sanbot.net;

/**
 * Created by admin on 2017/12/25.
 */

public class CompanyDevice {
    public static final int TYPE_DEVICE = 1;

    public static final int TYPE_FACESERVER = 2;
    private int companyId;

    private int uid;

    private int type;

    private String name;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
