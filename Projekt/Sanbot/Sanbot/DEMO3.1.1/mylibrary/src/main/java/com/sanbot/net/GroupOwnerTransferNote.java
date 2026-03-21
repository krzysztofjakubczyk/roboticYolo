package com.sanbot.net;

/**
 * Created by admin on 2017/4/6.
 */

public class GroupOwnerTransferNote {
    private int groupId;

    private int oldOwner;

    private int newOwner;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getOldOwner() {
        return oldOwner;
    }

    public void setOldOwner(int oldOwner) {
        this.oldOwner = oldOwner;
    }

    public int getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(int newOwner) {
        this.newOwner = newOwner;
    }

}
