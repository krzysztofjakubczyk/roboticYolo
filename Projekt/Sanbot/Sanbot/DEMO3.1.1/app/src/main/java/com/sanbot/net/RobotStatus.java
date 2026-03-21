package com.sanbot.net;


/**
 * Created by admin on 2017/3/2.
 * <p>
 * typedef struct
 * {
 * unsigned int status;
 * unsigned int speed;
 * unsigned int pos;
 * unsigned int reserve;
 * }part_status_t;
 * <p>
 * typedef struct
 * {
 * unsigned int obstacle;
 * part_status_t foot;
 * part_status_t hand_left;
 * part_status_t hend_right;
 * part_status_t head_horizontal;
 * part_status_t head_vertical;
 * part_status_t part_reserve[2] ;
 * }motion_status_t;
 */

public class RobotStatus {
    public static final int OBS_FRONT_LOW = 0;

    public static final int OBS_FRONT_HIGH = 1;

    public static final int OBS_FRONT_FALL = 2;

    public static final int OBS_FRONT_ARM = 3;

    public static final int OBS_LEFT = 4;

    public static final int OBS_RIGHT = 5;

    public static final int OBS_MID = 6;

    public static final int FOOT_FORWARD = 1;
    public static final int FOOT_BACKWARD = 2;
    public static final int FOOT_LEFT = 3;
    public static final int FOOT_RIGHT = 4;
    public static final int FOOT_STOP = 5;
    public static final int FOOT_IDLE = 0;

    private int obstacle;//避障
    //脚部状态 向前：1 后退:2 左转：3 右转：4 停止：5
    private int foot_status;
    //运行速度
    private int foot_speed;
    //
    private int foot_pos;

    private int foot_reserve;
    //左手状态 起：1 下:2  停止：5
    private int hand_left_status;

    private int hand_left_speed;

    private int hand_left_pos;

    private int hand_left_reserve;
    //右手状态 起：3 下:4  停止：5
    private int hand_right_status;

    private int hand_right_speed;

    private int hand_right_pos;

    private int hand_right_reserve;

    private int head_horizontal_status;

    private int head_horizontal_speed;

    private int head_horizontal_pos;

    private int head_horizontal_reserve;

    private int head_vertical_status;

    private int head_vertical_speed;

    private int head_vertical_pos;

    private int head_vertical_reserve;

    private int v3d_obs_info;

    private int ir1;

    private int ir2;

    private int ir3;

    private int ir4;

    private int ir5;

    private int ir6;

    private int ir7;

    private int ir8;

    private int ir9;

    private int ir10;

    private int ir11;

    private int ir12;

    private int ir13;

    private int ir14;

    private int ir15;

    private int ir16;

    private int ir17;

    private int ir18;

    private int ir19;

    private int ir20;


    public int getObstacle() {
        return obstacle;
    }

    public void setObstacle(int obstacle) {
        this.obstacle = obstacle;
    }

    public int getFoot_status() {
        return foot_status;
    }

    public void setFoot_status(int foot_status) {
        this.foot_status = foot_status;
    }

    public int getFoot_speed() {
        return foot_speed;
    }

    public void setFoot_speed(int foot_speed) {
        this.foot_speed = foot_speed;
    }

    public int getFoot_pos() {
        return foot_pos;
    }

    public void setFoot_pos(int foot_pos) {
        this.foot_pos = foot_pos;
    }

    public int getFoot_reserve() {
        return foot_reserve;
    }

    public void setFoot_reserve(int foot_reserve) {
        this.foot_reserve = foot_reserve;
    }

    public int getHand_left_status() {
        return hand_left_status;
    }

    public void setHand_left_status(int hand_left_status) {
        this.hand_left_status = hand_left_status;
    }

    public int getHand_left_speed() {
        return hand_left_speed;
    }

    public void setHand_left_speed(int hand_left_speed) {
        this.hand_left_speed = hand_left_speed;
    }

    public int getHand_left_pos() {
        return hand_left_pos;
    }

    public void setHand_left_pos(int hand_left_pos) {
        this.hand_left_pos = hand_left_pos;
    }

    public int getHand_left_reserve() {
        return hand_left_reserve;
    }

    public void setHand_left_reserve(int hand_left_reserve) {
        this.hand_left_reserve = hand_left_reserve;
    }

    public int getHand_right_status() {
        return hand_right_status;
    }

    public void setHand_right_status(int hand_right_status) {
        this.hand_right_status = hand_right_status;
    }

    public int getHand_right_speed() {
        return hand_right_speed;
    }

    public void setHand_right_speed(int hand_right_speed) {
        this.hand_right_speed = hand_right_speed;
    }

    public int getHand_right_pos() {
        return hand_right_pos;
    }

    public void setHand_right_pos(int hand_right_pos) {
        this.hand_right_pos = hand_right_pos;
    }

    public int getHand_right_reserve() {
        return hand_right_reserve;
    }

    public void setHand_right_reserve(int hand_right_reserve) {
        this.hand_right_reserve = hand_right_reserve;
    }

    public int getHead_horizontal_status() {
        return head_horizontal_status;
    }

    public void setHead_horizontal_status(int head_horizontal_status) {
        this.head_horizontal_status = head_horizontal_status;
    }

    public int getHead_horizontal_speed() {
        return head_horizontal_speed;
    }

    public void setHead_horizontal_speed(int head_horizontal_speed) {
        this.head_horizontal_speed = head_horizontal_speed;
    }

    public int getHead_horizontal_pos() {
        return head_horizontal_pos;
    }

    public void setHead_horizontal_pos(int head_horizontal_pos) {
        this.head_horizontal_pos = head_horizontal_pos;
    }

    public int getHead_horizontal_reserve() {
        return head_horizontal_reserve;
    }

    public void setHead_horizontal_reserve(int head_horizontal_reserve) {
        this.head_horizontal_reserve = head_horizontal_reserve;
    }

    public int getHead_vertical_status() {
        return head_vertical_status;
    }

    public void setHead_vertical_status(int head_vertical_status) {
        this.head_vertical_status = head_vertical_status;
    }

    public int getHead_vertical_speed() {
        return head_vertical_speed;
    }

    public void setHead_vertical_speed(int head_vertical_speed) {
        this.head_vertical_speed = head_vertical_speed;
    }

    public int getHead_vertical_pos() {
        return head_vertical_pos;
    }

    public void setHead_vertical_pos(int head_vertical_pos) {
        this.head_vertical_pos = head_vertical_pos;
    }

    public int getHead_vertical_reserve() {
        return head_vertical_reserve;
    }

    public void setHead_vertical_reserve(int head_vertical_reserve) {
        this.head_vertical_reserve = head_vertical_reserve;
    }

    /**
     * 底部 前方避障
     *
     * @return
     */
    public boolean isObsFrontLow() {
        return checkStatus(OBS_FRONT_LOW);
    }

    /**
     * 身体中间
     *
     * @return
     */
    public boolean isObsFrontHight() {
        return checkStatus(OBS_FRONT_HIGH);
    }

    /**
     * @return
     */
    public boolean isObsFrontArm() {
        return checkStatus(OBS_FRONT_ARM);
    }

    /**
     * 左侧
     *
     * @return
     */
    public boolean isObsLeft() {
        return checkStatus(OBS_LEFT);
    }

    /**
     * 右侧
     *
     * @return
     */
    public boolean isObsRight() {
        return checkStatus(OBS_RIGHT);
    }

    /**
     * 中间
     *
     * @return
     */
    public boolean isObsMiddle() {
        return checkStatus(OBS_MID);
    }

    /**
     * 防摔
     *
     * @return
     */
    public boolean isObsFrontFall() {
        return checkStatus(OBS_FRONT_FALL);
    }

    public boolean checkStatus(int index) {
        int temp = getObstacle() & (0x1 << index);
        return temp != 0;
    }

    public int getIr1() {
        return ir1;
    }

    public void setIr1(int ir1) {
        this.ir1 = ir1;
    }

    public int getIr2() {
        return ir2;
    }

    public void setIr2(int ir2) {
        this.ir2 = ir2;
    }

    public int getIr3() {
        return ir3;
    }

    public void setIr3(int ir3) {
        this.ir3 = ir3;
    }

    public int getIr4() {
        return ir4;
    }

    public void setIr4(int ir4) {
        this.ir4 = ir4;
    }

    public int getIr5() {
        return ir5;
    }

    public void setIr5(int ir5) {
        this.ir5 = ir5;
    }

    public int getIr6() {
        return ir6;
    }

    public void setIr6(int ir6) {
        this.ir6 = ir6;
    }

    public int getIr7() {
        return ir7;
    }

    public void setIr7(int ir7) {
        this.ir7 = ir7;
    }

    public int getIr8() {
        return ir8;
    }

    public void setIr8(int ir8) {
        this.ir8 = ir8;
    }

    public int getIr9() {
        return ir9;
    }

    public void setIr9(int ir9) {
        this.ir9 = ir9;
    }

    public int getIr10() {
        return ir10;
    }

    public void setIr10(int ir10) {
        this.ir10 = ir10;
    }

    public int getIr11() {
        return ir11;
    }

    public void setIr11(int ir11) {
        this.ir11 = ir11;
    }

    public int getIr12() {
        return ir12;
    }

    public void setIr12(int ir12) {
        this.ir12 = ir12;
    }

    public int getIr13() {
        return ir13;
    }

    public void setIr13(int ir13) {
        this.ir13 = ir13;
    }

    public int getIr14() {
        return ir14;
    }

    public void setIr14(int ir14) {
        this.ir14 = ir14;
    }

    public int getIr15() {
        return ir15;
    }

    public void setIr15(int ir15) {
        this.ir15 = ir15;
    }

    public int getIr16() {
        return ir16;
    }

    public void setIr16(int ir16) {
        this.ir16 = ir16;
    }

    public int getIr17() {
        return ir17;
    }

    public void setIr17(int ir17) {
        this.ir17 = ir17;
    }

    public int getIr18() {
        return ir18;
    }

    public void setIr18(int ir18) {
        this.ir18 = ir18;
    }

    public int getIr19() {
        return ir19;
    }

    public void setIr19(int ir19) {
        this.ir19 = ir19;
    }

    public int getIr20() {
        return ir20;
    }

    public void setIr20(int ir20) {
        this.ir20 = ir20;
    }

    public int getV3d_obs_info() {
        return v3d_obs_info;
    }

    public void setV3d_obs_info(int v3d_obs_info) {
        this.v3d_obs_info = v3d_obs_info;
    }


    public int[] forwardIr = {ir11, ir12, ir13, ir6, ir7};
    public int[] leftIr = {ir3, ir4, ir5, ir16, ir17};
    public int[] rightIr = {ir8, ir9, ir10, ir14, ir15};


    public int getState(int[] srcInt) {
        updateRight();
        updateLeft();

        int num = getMinVal(srcInt);
        if (num < 30 && num > 0) {
            return STATE_WARNING;
        } else {
            return STATE_UNLIMIT;
        }

    }

    public static int getMinVal(int[] srcInt) {
        int min = Integer.MAX_VALUE;
        for (int i : srcInt) {
            if (i < min && i != 0) {
                min = i;
            }
        }

        if (min == Integer.MAX_VALUE) {
            min = 0;
        }
        return min;
    }

    private void updateForward() {
        forwardIr[0] = ir11;
        forwardIr[1] = ir12;
        forwardIr[2] = ir13;
        forwardIr[3] = ir6;
        forwardIr[4] = ir7;
    }

    private void updateLeft() {
        leftIr[0] = ir3;
        leftIr[1] = ir4;
        leftIr[2] = ir5;
        leftIr[3] = ir16;
        leftIr[4] = ir17;
    }

    private void updateRight() {
        rightIr[0] = ir8;
        rightIr[1] = ir9;
        rightIr[2] = ir10;
        rightIr[3] = ir14;
        rightIr[4] = ir15;
    }

    public int getForwardState(int[] srcInt) {
        updateForward();
        int num = getMinVal(srcInt);
        if (num < 30 && num > 0) {
            if (num < 20) {
                return STATE_OBL;
            }
            return STATE_WARNING;
        } else {
            return STATE_UNLIMIT;
        }
    }

    final public static int STATE_UNLIMIT = 1;
    final public static int STATE_WARNING = 1 << 1;
    final public static int STATE_OBL = 1 << 2;


}
