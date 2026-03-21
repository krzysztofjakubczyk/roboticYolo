package com.sanbot.demo.manager.model.biz;

/**
 * 机器人业务逻辑接口
 */
public interface IRobot {

    int onRobotMove(int uid, int bodyPart, int cmd, float power, long seq);

    /**
     * 打开视频
     * @return 返回值： 0-失败，会话ID
     */
    long onOpenVideo(int uid, int channel, int type, int streamType);

    /**
     * 关闭视频
     * @param sessionId 　会话ID　onOpenVideo返回值
     * @return 返回值： 0-成功，其他-错误码
     */
    int onCloseVideo(long sessionId);

    /**
     * 设置视频流类型
     *
     * @param sessionId 会话ID　onOpenVideo返回值
     * @param streamType 0-音视频、1-视频、2-音频。
     * @return
     */
    int onChangeVideoStream(long sessionId, int streamType);


}
