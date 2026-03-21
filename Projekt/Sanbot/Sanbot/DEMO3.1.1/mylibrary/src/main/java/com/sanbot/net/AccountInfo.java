package com.sanbot.net;

/**
 * Created by QHPC on 2016/4/19.
 * 账号字段信息
 *
 */
public class AccountInfo {
    private String account;//帐号

    private String valueType;//字段类型

    private String valueString;//字符串

    public AccountInfo(String account, String valueType, String valueString)
    {
        this.account = account;
        this.valueType = valueType;
        this.valueString = valueString;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
}