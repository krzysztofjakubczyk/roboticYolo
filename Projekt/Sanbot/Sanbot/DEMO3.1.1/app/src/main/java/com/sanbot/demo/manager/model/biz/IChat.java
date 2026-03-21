package com.sanbot.demo.manager.model.biz;

import com.sanbot.demo.entity.Message;
import com.sanbot.net.ServerInfo;

import java.util.List;

/**
 * 聊天业务逻辑接口
 */
public interface IChat {

    /**
     * 获取登录用户uid
     * @return uid
     */
    int getUid();

    /**
     * 文件id 转 url
     * @param fileId 文件id
     * @param seq 操作码
     * @return 返回值：0-成功，其他-错误码
     */
    int getUrlById(long fileId, long seq);

    /**
     * 上传文件
     * @param dstUid 对方uid
     * @param fileName 文件路径
     * @param fileType 文件类型
     * @param fileSize 文件大小
     * @param seq 操作码
     * @return 返回值：0-成功，其他-错误码
     */
    int onUploadFile(int dstUid, String fileName, int fileType, long fileSize, long seq);

    /**
     * 上传文件完成
     * @param md5 文件md5
     * @param type 文件类型
     * @param size 文件大小
     * @param seq 操作码
     * @return 返回值：0-成功，其他-错误码
     */
    int onUploadFileFinish(String md5, int type, long size, long seq);

    /**
     * 发送消息
     * @param uid 对方uid
     * @param data 数据
     * @param len 数据长度
     * @param type 消息类型
     * @param seq 操作码
     * @return 返回值：0-成功，其他-错误码
     */
    int onSendMsg(int uid, byte[] data, int len, int type, long seq);

    /**
     * 获取服务器信息
     */
    ServerInfo getServerInfo();


    /**
     * 更新本地数据库消息（若有更新，没有添加）
     * @param message 消息对象
     * @return 消息id
     */
    long update(Message message);

    /**
     * 查询消息
     * @param toId 对方id
     * @param fromId 用户id
     * @return 消息列表
     */
    List<Message> queryById(int toId, int fromId, int offset);

    /**
     * 查询所有消息
     * @return 消息列表
     */
    List<Message> query();

}
