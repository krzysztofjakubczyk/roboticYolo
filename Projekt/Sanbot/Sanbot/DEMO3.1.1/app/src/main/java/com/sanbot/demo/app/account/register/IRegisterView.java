package com.sanbot.demo.app.account.register;

import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.app.country.Country;

/**
 * 注册操作类
 * @author youngbin
 * {@link com.sanbot.demo.app.account.login.ILoginView}
 */

public interface IRegisterView extends IBaseView{

    String getUser();

    void setUser(String user);

    String getPassword();

    void setPassword(String password);

    String getSmsCode();

    void setSmsCode(String code);

    Country getCountry();
}
