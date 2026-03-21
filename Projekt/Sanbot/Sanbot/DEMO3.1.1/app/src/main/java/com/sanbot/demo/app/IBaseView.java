package com.sanbot.demo.app;


/**
 * @author youngbin
 *
 * 视图操作基类
 */

public interface IBaseView {

    /**
     * 成功时调用
     */
    void onSuccess();

    /**
     * 返回时调用
     * @param errorMsg 错误消息
     */
    void onFailed(String errorMsg);

    /**
     * 显示toast
     * @param msg 消息内容
     */
    void showMsg(String msg);
}
