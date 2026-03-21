package com.sanbot.net;

/**
 * Created by admin on 2017/5/12.
 */

public class GroupList {
    private int groupId;
    //是否在通讯录中 0=不在通讯录中，1=在通讯录中 default=0
    private int status;
    // 是否免打扰模式1=是，0为接收消息 default=0
    private int disturb;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDisturb() {
        return disturb;
    }

    public void setDisturb(int disturb) {
        this.disturb = disturb;
    }
}
