package com.sanbot.demo.manager.model.biz;


/**
 * 登录业务逻辑接口
 */
public interface ILogin {

    /**
     * 登录
     * @param user 用户名
     * @param password 密码
     * @return 返回值：0-成功，其他-错误码
     */
    int login(String user, String password, String country);

}
