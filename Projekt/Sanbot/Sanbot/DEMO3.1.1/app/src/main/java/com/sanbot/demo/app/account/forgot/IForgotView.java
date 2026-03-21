package com.sanbot.demo.app.account.forgot;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.app.country.Country;

/**
 * @author youngbin
 * 忘记密码操作类
 * {@link com.sanbot.demo.app.account.login.ILoginView}
 */

public interface IForgotView extends IBaseView{

    String getUser();

    void setUser(String user);

    String getPassword();

    void setPassword(String password);

    String getSmsCode();

    void setSmsCode(String code);

    Country getCountry();
}
