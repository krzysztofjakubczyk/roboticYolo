package com.sanbot.net;

/**
 * 用户个人信息版本号
 * Created by QH on 2017/1/7.
 */

public class FriendVersion {
    //用户UID
    private int uid;
    //用户基本信息版本号
    private int base_version;
    //用户备注信息版本号
    private int remarks_version;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBase_version() {
        return base_version;
    }

    public void setBase_version(int base_version) {
        this.base_version = base_version;
    }

    public int getRemarks_version() {
        return remarks_version;
    }

    public void setRemarks_version(int remarks_version) {
        this.remarks_version = remarks_version;
    }
}
