package com.sanbot.demo.app.start.main.friend;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.entity.UserInfo;

import java.util.List;


/**
 * 好友列表操作类
 *
 * @author youngbin
 */
public interface IFriendView extends IBaseView {

    void setAdapter(List<UserInfo> list);

}
