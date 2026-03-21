package com.sanbot.net;

/**
 * Created by QH on 2017/1/19.
 */
public class FriendPermission {
    private int dev_uid;

    private int friend_uid;

    private int permission;

    public int getFriend_uid() {
        return friend_uid;
    }

    public void setFriend_uid(int friend_uid) {
        this.friend_uid = friend_uid;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getDev_uid() {

        return dev_uid;
    }

    public void setDev_uid(int dev_uid) {
        this.dev_uid = dev_uid;
    }
}
