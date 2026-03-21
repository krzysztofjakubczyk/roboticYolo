package com.sanbot.demo.manager.model;

import com.sanbot.demo.manager.model.biz.ILogin;
import com.sanbot.demo.util.StringUtil;
import com.sanbot.net.LoginAccount;
import com.sanbot.net.NetApi;



public class LoginImp extends Base implements ILogin {

    @Override
    public int login(String user, String password, String countryCode) {
        LoginAccount loginAccount = new LoginAccount();
        String type = "qlink_id";
        if (StringUtil.checkPhone(user)){
            type = "tel";
            if (!"+0086".equals(countryCode)) {
                user = countryCode+user;
            }
        }
        loginAccount.setAccountType(type);
        loginAccount.setAccount(user);
        loginAccount.setPassword(password);
        NetApi.getInstance().stopLogin();
        return mNetApi.onLogin(loginAccount);
    }
}
