package com.sanbot.net;

/**
 * Created by QH on 2017/1/19.
 */
public class RobotCmd {
    //部位
    public static final int BODY_HEAD = 1001;
    public static final int BODY_LEFT_HAND = 2001;
    public static final int BODY_RIGHT_HAND = 2002;
    public static final int BODY_FOOT = 3001;
    //动作
    public static final int CMD_MOVE_LEFT = 1003;
    public static final int CMD_MOVE_RIGHT = 1004;
    public static final int CMD_MOVE_UP = 1001;
    public static final int CMD_MOVE_DOWN = 1002;
    public static final int CMD_MOVE_STOP = 1005;

    public static final int SEND_TYPE_POST = 0; //无回调方式
    public static final int SEND_TYPE_GET = 1;//有回调方式

    private int devUid;

    private int bodyPart;

    private int moveCmd;

    private int sendType = 0;

    private int speed;

    private int companyMode = 0; //

    private int companyId;

    public int getCompanyMode() {
        return companyMode;
    }

    public void setCompanyMode(int companyMode) {
        this.companyMode = companyMode;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDevUid() {
        return devUid;
    }

    public void setDevUid(int devUid) {
        this.devUid = devUid;
    }

    public int getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(int bodyPart) {
        this.bodyPart = bodyPart;
    }

    public int getMoveCmd() {
        return moveCmd;
    }

    public void setMoveCmd(int moveCmd) {
        this.moveCmd = moveCmd;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }
}
