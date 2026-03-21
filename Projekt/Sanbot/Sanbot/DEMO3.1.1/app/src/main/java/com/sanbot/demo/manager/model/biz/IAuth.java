package com.sanbot.demo.manager.model.biz;


/**
 * 验证码登录业务逻辑接口
 */
public interface IAuth {

    /**
     * 获取验证码
     * @param phone 手机号码
     * @return 返回值：0-成功，其他-错误码
     */
    int getSmsCode(String phone, String countryCode);

    /**
     * 验证码登录
     * @param tel 手机号码
     * @param code 验证码
     * @return 返回值：0-成功，其他-错误码
     */
    int loginBySmsCoder(String tel, String code, String countryCode);

}
