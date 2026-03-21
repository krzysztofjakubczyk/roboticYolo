package com.sanbot.demo.app.start.main.robot;

import android.view.SurfaceView;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.net.RobotCmd;

/**
 * 机器人操作类
 *
 * @author youngbin
 */

public interface IRobotView extends IBaseView{

    /**
     * 获取机器人uid
     * @return uid
     */
    int getUid();


    /**
     * 机器人部位（头，左右手，脚）
     * @return {@link RobotCmd#BODY_HEAD,RobotCmd#BODY_FOOT,RobotCmd#BODY_LEFT_HAND,RobotCmd#BODY_RIGHT_HAND}
     */
    int getBody();

    void setPlayer(String text);


    void setSelectRobot(String text);

    SurfaceView getSurfaceView();
}
