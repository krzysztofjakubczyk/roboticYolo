package com.sanbot.net;

/**
 * Created by QH on 2017/1/16.
 */

public class LoginAccount extends BaseAccount{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("account:");
        stringBuffer.append(getAccount());
        stringBuffer.append(" accountType:");
        stringBuffer.append(getAccountType());
        return stringBuffer.toString();
    }
}
