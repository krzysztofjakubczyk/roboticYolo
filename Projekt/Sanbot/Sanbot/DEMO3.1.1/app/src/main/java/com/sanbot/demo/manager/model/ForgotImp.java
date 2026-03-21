package com.sanbot.demo.manager.model;

import com.sanbot.demo.manager.model.biz.IForgot;
import com.sanbot.net.AccountPassword;

/**
 * Created by admin on 2017/4/18.
 */

public class ForgotImp extends Base implements IForgot {

    @Override
    public int getSmsCode(String tel, String countryCode) {
        if (!"+0086".equals(countryCode)) {
            tel = countryCode+tel;
        }
        return mNetApi.onGetResetPwdPhoneidentify(tel);
    }

    @Override
    public int resetPassword(String tel, String password, String code, String countryCode) {
        if (!"+0086".equals(countryCode)) {
            tel = countryCode+tel;
        }
        AccountPassword accountPassword = new AccountPassword();
        accountPassword.setAccount(tel);
        accountPassword.setAccountType("tel");
        accountPassword.setPassword(password);
        accountPassword.setIdentify(code);
        return mNetApi.onResetPwd(accountPassword);
    }
}
