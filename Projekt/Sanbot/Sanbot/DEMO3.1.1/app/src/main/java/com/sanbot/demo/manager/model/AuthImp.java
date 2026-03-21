package com.sanbot.demo.manager.model;

import com.sanbot.demo.manager.model.biz.IAuth;
import com.sanbot.demo.util.PhoneCountryUtil;
import com.sanbot.net.IdentifyInfo;

public class AuthImp extends Base implements IAuth {

    @Override
    public int getSmsCode(String phone, String countryCode) {

        if (!"+0086".equals(countryCode)) {
            phone = countryCode+phone;
        }
        return mNetApi.onGetLoginIdentify(phone);
    }

    @Override
    public int loginBySmsCoder(String tel, String code, String countryCode) {
        PhoneCountryUtil util = new PhoneCountryUtil();
        int areaCode = util.getAreaCode(countryCode);
        if (!"+0086".equals(countryCode)) {
            tel = countryCode+tel;
        }
        IdentifyInfo info = new IdentifyInfo();
        info.setAreaCode(areaCode);
        info.setIdentify(code);
        info.setTel(tel);
        return mNetApi.onLoginByIdentify(info);
    }
}
