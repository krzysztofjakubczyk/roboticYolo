package com.sanbot.net;

/**
 * Created by QHPC on 2016/4/19.
 * 回应好友请求信息
 * A请求加B为好友，B通过该消息回应该A
 * A收到该消息
 */
public class AnswerMsg {
    private int answer_uid;
    private int answer_type;
    private int robotAdmin;

    public  AnswerMsg(int answer_uid, int answer_type, int admin)
    {
        this.answer_type = answer_type;
        this.answer_uid = answer_uid;
        this.robotAdmin = admin;
    }

    public int getAnswer_uid() {
        return answer_uid;
    }

    public void setAnswer_uid(int answer_uid) {
        this.answer_uid = answer_uid;
    }

    public int getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(int answer_type) {
        this.answer_type = answer_type;
    }

    public int getRobotAdmin() {
        return robotAdmin;
    }

    public void setRobotAdmin(int robotAdmin) {
        this.robotAdmin = robotAdmin;
    }
}
