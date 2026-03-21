package com.sanbot.net;

/**
 * Created by admin on 2017/6/9.
 */

public class GroupStatus {
    public static final int DISTURB_MODE = 1;
    public static final int NORMAL_MODE = 0;
    private int groupId;
    //1-开启免打扰，0-关闭免打扰。
    private int mode;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
