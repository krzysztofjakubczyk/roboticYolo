package com.sanbot.net;

/**
 * Created by QHPC on 2016/4/19.
 * 回应添加好友消息
 * A收到B的请求加好友消息，B向A回应该消息
 * A收到该消息
 */
public class ResponseFriend {
    private int ask_user_id;
    private int answer_user_id;
    private int answer_type;
    private int msg_id;

    public  ResponseFriend(int ask_user_id, int answer_user_id, int answer_type, int msg_id)
    {
        this.ask_user_id = ask_user_id;
        this.answer_user_id = answer_user_id;
        this.answer_type = answer_type;
        this.msg_id = msg_id;
    }

    public int getAsk_user_id() {
        return ask_user_id;
    }

    public void setAsk_user_id(int ask_user_id) {
        this.ask_user_id = ask_user_id;
    }

    public int getAnswer_user_id() {
        return answer_user_id;
    }

    public void setAnswer_user_id(int answer_user_id) {
        this.answer_user_id = answer_user_id;
    }

    public int getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(int answer_type) {
        this.answer_type = answer_type;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }
}
