package com.sanbot.demo.app.start.main.chat;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.entity.Message;

import java.util.List;

/**
 * 消息操作类
 *
 * @author youngbin
 */

public interface IChatView extends IBaseView {


    int getCount();

    /**
     * 获取对方uid
     *
     * @return uid
     */
    int getToUid();

    /**
     * 获取文字内容
     *
     * @return text
     */
    String getContent();

    /**
     * 设置文字内容，一般用于清空
     *
     * @param content text
     */
    void setContent(String content);

    /**
     * 添加数据
     *
     * @param toUid uid
     * @param list  数据
     */
    void addAdapter(int fromUid, List<Message> list);

    void addMessage(int fromUid, Message message);

    /**
     * 刷新
     */
    void notifyData();

}
