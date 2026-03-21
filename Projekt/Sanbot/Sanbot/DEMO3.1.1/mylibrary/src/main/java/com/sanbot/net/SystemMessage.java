package com.sanbot.net;

/**
 * Created by QH on 2017/1/18.
 */

public class SystemMessage {

    public final static int ADD_FRIEND_REQUEST = 1;
    public final static int RESPONSE_FRIEND_ADD = 2;
    public final static int ROBOT_ADMIN_CHANGE = 3;
    public final static int PC_ONLINE_TRUE = 4;
    public final static int PC_ONLINE_FALSE = 5;
    public final static int DELETE_FRIEND_USER = 6;

    // 消息id序号
    private int msg_id;
    // 1-好友添加  2-好友同意 3-管理者权限转移,4-pc在线，5-pc不在线 6-删除好友
    private int note_type;

    private int note_time;
    // 源uid  （源uid用于，用户A修改、请求功能于用户B时使用。）
    private int src_uid;
    // 设备uid （源uid修改、设备功能时标示设备。）
    private int dev_uid;
    // 目标uid  （目标uid用于，用户A修改、请求功能于用户B时使用。）
    private int dst_uid;
    // 添加好友的应答类型
    private int answer_type;
    // 通知内容
    private String msg_value;

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getNote_type() {
        return note_type;
    }

    public void setNote_type(int note_type) {
        this.note_type = note_type;
    }

    public int getNote_time() {
        return note_time;
    }

    public void setNote_time(int note_time) {
        this.note_time = note_time;
    }

    public int getSrc_uid() {
        return src_uid;
    }

    public void setSrc_uid(int src_uid) {
        this.src_uid = src_uid;
    }

    public int getDev_uid() {
        return dev_uid;
    }

    public void setDev_uid(int dev_uid) {
        this.dev_uid = dev_uid;
    }

    public int getDst_uid() {
        return dst_uid;
    }

    public void setDst_uid(int dst_uid) {
        this.dst_uid = dst_uid;
    }

    public int getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(int answer_type) {
        this.answer_type = answer_type;
    }

    public String getValue() {
        return msg_value;
    }

    public void setValue(String value) {
        this.msg_value = value;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
                "note_type=" + note_type +
                ", src_uid='" + src_uid + '\'' +
                ", dst_uid='" + dst_uid + '\'' +
                ", dev_uid=" + dev_uid +
                ", answer_type='" + answer_type + '\'' +
                ", msg_value='" + msg_value + '\'' +
                '}';
    }
}
