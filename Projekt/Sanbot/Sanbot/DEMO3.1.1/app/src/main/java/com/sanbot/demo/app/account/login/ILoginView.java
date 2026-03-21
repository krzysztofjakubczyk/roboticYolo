package com.sanbot.demo.app.account.login;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.app.country.Country;

/**
 * 登录操作类
 * @author youngbin
 */
public interface ILoginView extends IBaseView{

    /**
     * 获取用户名
     * @return (手机号码，用户名，邮箱)
     */
    String getUser();

    /**
     * 设置用户名
     * @param user 用户名
     */
    void setUser(String user);

    /**
     * 获取密码
     * @return 密码
     */
    String getPassword();

    /**
     * 设置密码
     * @param password 密码
     */
    void setPassword(String password);

    Country getCountry();

}
