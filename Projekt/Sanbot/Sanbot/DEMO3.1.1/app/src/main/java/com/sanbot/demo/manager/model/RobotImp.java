package com.sanbot.demo.manager.model;

import com.sanbot.demo.manager.model.biz.IRobot;
import com.sanbot.net.RobotCmd;
import com.sanbot.net.VideoHandle;
import com.sanbot.net.VideoInfo;

/**
 * Created by admin on 2017/4/18.
 */

public class RobotImp extends Base implements IRobot {

    @Override
    public int onRobotMove(int uid, int bodyPart, int cmd, float power, long seq) {
        RobotCmd robotCmd = new RobotCmd();
        robotCmd.setDevUid(uid);
        robotCmd.setBodyPart(bodyPart);
        robotCmd.setMoveCmd(cmd);
        robotCmd.setSpeed((int) (power * 10));
        robotCmd.setSendType(1);
        return mNetApi.onRobotMove(robotCmd, seq);
    }

    @Override
    public long onOpenVideo(int uid, int channel, int type, int streamType) {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setDev_id(uid);
        videoInfo.setChannel(channel);
        videoInfo.setType(type);
        videoInfo.setStreamContents(streamType);
        VideoHandle videoHandle = mNetApi.onOpenVideo(videoInfo);
        if (videoHandle == null) return 410010;

        return (videoHandle != null && videoHandle.getHandle() != 0) ? videoHandle.getHandle() : videoHandle.getReasonCode();
    }

    @Override
    public int onCloseVideo(long sessionId) {
        return mNetApi.onCloseVideo(sessionId);
    }

    @Override
    public int onChangeVideoStream(long sessionId, int streamType) {
        return mNetApi.onChangeVideoStream(sessionId, streamType);
    }
}
