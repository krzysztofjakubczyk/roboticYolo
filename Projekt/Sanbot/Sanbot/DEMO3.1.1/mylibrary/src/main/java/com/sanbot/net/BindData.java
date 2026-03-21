package com.sanbot.net;

/**
 * Created by admin on 2017/12/19.
 */

public class BindData {
    public static final int TEL_TYPE = 1;

    public static final int EMAIL_TYPE = 2;

    // 修改类型1-手机号, 2-邮箱。
    private int type;
    //新值
    private String value;
    //验证码
    private String identifyCode;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }
}
