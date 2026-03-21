package com.sanbot.demo.manager.model.biz;

import com.sanbot.demo.entity.UserInfo;

public interface IFriendDetail {

    UserInfo queryById(int uid);

    int setRemark(int uid, String remark, long seq);

}
