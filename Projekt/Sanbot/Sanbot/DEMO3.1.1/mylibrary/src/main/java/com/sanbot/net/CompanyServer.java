package com.sanbot.net;

/**
 * Created by admin on 2017/11/7.
 */

public class CompanyServer {
    private int uid;// 用户uid 添加时不需要输入

    private String name;// 用户名称

    private byte[] nameData;

    private String account;// 人脸服务器64位id

    private int version;// 用户版本号 添加时不需要输入

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getVersion() {
        return version;
    }
}
