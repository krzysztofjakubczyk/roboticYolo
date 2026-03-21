package com.sanbot.demo.manager.model.biz;

import com.sanbot.demo.entity.Message;

/**
 * 视频业务逻辑接口
 */
public interface IVideo {

    /**
     * 获取登录用户uid
     *
     * @return uid
     */
    int getUid();

    /**
     * 发送消息
     *
     * @param uid  对方uid
     * @param data 数据
     * @param len  数据长度
     * @param type 消息类型
     * @param seq  操作码
     * @return 返回值：0-成功，其他-错误码
     */
    int onSendMsg(int uid, byte[] data, int len, int type, long seq);


    /**
     * 更新本地数据库消息（若有更新，没有添加）
     * @param message 消息对象
     * @return 消息id
     */
    long update(Message message);

}
