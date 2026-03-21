package com.sanbot.demo.manager.model.biz;

/**
 * Created by admin on 2017/4/18.
 */

public interface IRegister {

    int getSmsCode(String tel, String countryCode);

    int register(String tel, String password, String code, String countryCode);

}
