package com.sanbot.net;

/**
 * Created by QHPC on 2016/4/19.
 * 好友基本信息版本记录
 * 通过比对版本号判断用户信息是否改变
 * 版本号不一致则说明信息变化需要更新信息
 */
public class FriendVersionInfo {
    private int uid;

    private int base_version;  //  user basic information

    private int remark_version; //  user mark information

  /*  public FriendVersionInfo(int uid, int base_version, int remark_version)
    {
        this.uid            = uid;
        this.base_version   = base_version;
        this.remark_version = remark_version;
    }*/

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

    public int getRemark_version() {
        return remark_version;
    }

    public void setRemark_version(int remark_version) {
        this.remark_version = remark_version;
    }
}
