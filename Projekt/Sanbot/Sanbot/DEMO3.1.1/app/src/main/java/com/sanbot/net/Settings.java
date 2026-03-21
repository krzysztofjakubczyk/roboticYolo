package com.sanbot.net;

/**
 * uid: 接收方用户UID
 * params 发送给对方的内容数据
 * type 命令字
 * sendType 发送类型 0：有回调 1 为单向无回调
 * companyMode 企业通讯模式 0为非企业 1为企业
 * companyId 企业ID
 * Created by QH on 2017/1/16.
 */
public class Settings {
    public final static int SENDTYPE_CALLBACK = 0;

    public final static int SENDTYPE_NOCALLBACK = 1;

    public final static int NOT_COMPANY = 0;

    public final static int IS_COMPANY = 1;

    private int uid;

    private String params;

    private int type;

    private int sendType = 0;

    private int companyMode = 0; //

    private int companyId;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getCompanyMode() {
        return companyMode;
    }

    public void setCompanyMode(int companyMode) {
        this.companyMode = companyMode;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "uid=" + uid +
                ", params='" + params + '\'' +
                ", type=" + type +
                ", sendType=" + sendType +
                ", companyMode=" + companyMode +
                ", companyId=" + companyId +
                '}';
    }
}
