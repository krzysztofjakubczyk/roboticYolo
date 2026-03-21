package com.sanbot.net;

/**
 * @param  ask_uid　添加方user_id
 * @param dev_uid 设备UID　
 * @param type　回应类型　 应答类型，0-拒绝，1-同意。
 * @param msg_id　消息ID
 * @param permission　权限
 * @param seq　
 * Created by QH on 2017/1/16.
 */

public class ResponseBeFriend {
    private int ask_uid;

    private int dev_uid;

    private  int type;

    private int msg_id;

    private long permission;

    public int getAsk_uid() {
        return ask_uid;
    }

    public void setAsk_uid(int ask_uid) {
        this.ask_uid = ask_uid;
    }

    public int getDev_uid() {
        return dev_uid;
    }

    public void setDev_uid(int dev_uid) {
        this.dev_uid = dev_uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public long getPermission() {
        return permission;
    }

    public void setPermission(long permission) {
        this.permission = permission;
    }
}
