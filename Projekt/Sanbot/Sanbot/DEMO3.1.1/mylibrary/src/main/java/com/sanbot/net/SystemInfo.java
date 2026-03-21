package com.sanbot.net;

/**
 * Created by QHPC on 2016/8/22.
 */
public class SystemInfo {
    public final static int ROBOT_ADMIN_CHANGE = 3;

    //消息ID
    private int msg_id;
    //消息类型
    private int note_type;
    //消息时间
    private int note_time;
    //发起者UID
    private int src_uid;
    //设备UID
    private int dev_uid;
    //接收者UID
    private int dst_uid;
    //答案类型
    private int answer_type;
    //描述内容
    private String  value;

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
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("msg_id:");
        buffer.append(msg_id);
        buffer.append(" note_type:");
        buffer.append(note_type);
        buffer.append(" note_time:");
        buffer.append(note_time);
        buffer.append(" src_uid:");
        buffer.append(src_uid);
        buffer.append(" dev_uid:");
        buffer.append(dev_uid);
        buffer.append(" answer_type:");
        buffer.append(answer_type);
        buffer.append(" value:");
        buffer.append(value);

        return buffer.toString();
    }
}
