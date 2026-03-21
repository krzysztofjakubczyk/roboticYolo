package com.sanbot.demo.manager.model;

import com.sanbot.demo.manager.model.biz.IRegister;
import com.sanbot.demo.util.PhoneCountryUtil;
import com.sanbot.net.AccountIdentify;
import com.sanbot.net.AccountRegister;

import java.util.Locale;

/**
 * Created by admin on 2017/4/18.
 */

public class RegisterImp extends Base implements IRegister {

    @Override
    public int getSmsCode(String tel, String countryCode) {
        PhoneCountryUtil util = new PhoneCountryUtil();
        int areaCode = util.getAreaCode(countryCode);
        AccountIdentify identify = new AccountIdentify();
        identify.setAccount(String.format(Locale.getDefault(), "qlink%s", tel.replaceAll("\\+","")));
        identify.setAreaCode(areaCode);
        if (!"+0086".equals(countryCode)) {
            tel = countryCode+tel;
        }
        identify.setTel(tel);
        return mNetApi.onGetRegistIdentify(identify);
    }

    @Override
    public int register(String tel, String password, String code, String countryCode) {
        PhoneCountryUtil util = new PhoneCountryUtil();
        int areaCode = util.getAreaCode(countryCode);
        AccountRegister register = new AccountRegister();
        if (!"+0086".equals(countryCode)) {
            tel = countryCode+tel;
        }
        register.setTel(tel);
        register.setAccount(String.format(Locale.getDefault(), "qlink%s", tel.replaceAll("\\+", "")));
        register.setPassword(password);
        register.setIdentify(code);
        register.setAccountType("tel");
        register.setAreaCode(areaCode);
        return mNetApi.onRegistAccount(register);
    }
}
