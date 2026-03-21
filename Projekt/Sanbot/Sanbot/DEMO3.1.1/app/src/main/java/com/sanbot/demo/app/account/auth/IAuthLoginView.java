package com.sanbot.demo.app.account.auth;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.app.country.Country;

/**
 * @author youngbin
 * 验证码登录操作类
 */
public interface IAuthLoginView extends IBaseView{

    String getUser();

    void setUser(String user);

    String getSmsCode();

    void setSmsCode(String smsCode);

    void startTimer();

    Country getCountry();

}
