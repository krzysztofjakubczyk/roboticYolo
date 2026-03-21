package com.sanbot.demo.manager.model.biz;

import com.sanbot.demo.entity.UserInfo;
import com.sanbot.net.BaseInfo;

import java.util.List;

/**
 * 好友列表业务逻辑接口
 */
public interface IFriend {

    /**
     * 获取好友uid列表
     *
     * @param seq 标识
     * @return 0 success
     */
    int getFriendUids(long seq);

    /**
     * 获取好友备注列表
     *
     * @param uids 好友uid列表
     * @param seq  标识
     * @return 0 success
     */
    int getFriendRemarks(List<Integer> uids, long seq);

    /**
     * 获取好友基本信息列表
     *
     * @param uids 好友uid列表
     * @param seq  标识
     * @return 0 success
     */
    int getFriendInfo(List<Integer> uids, long seq);


    /**
     * 获取头像url
     *
     * @param id  文件id
     * @param seq 标识
     * @return 0 success
     */
    int getAvatarUrlById(long id, long seq);


    /**
     * 获取所有好友
     * @return 好友list
     */
    List<UserInfo> query();

    /**
     * 查询机器人
     * @return 机器人list
     */
    List<UserInfo> queryRobot();

    /**
     * 获取好友
     * @param uid uid
     * @return 好友
     */
    UserInfo queryById(int uid);

    long update(UserInfo userInfo);

    long updateBaseInfo(BaseInfo baseInfo, int baseInfoVersion);

    long updateRemark(int uid, String remark, int remarkVersion);

    long updateAvatarUrl(int uid, String avatar);

    long deleteById(int uid);

    void clean();

}
