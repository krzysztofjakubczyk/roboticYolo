package com.sanbot.net;

/**
 * 帐号类型工具类
 * @param accountType　帐号类型 qlink_id, email, tel, face_svr(前台服务器)
 * @param account　帐号
 * Created by QH on 2017/1/16.
 */

public class BaseAccount {
    //帐号类型qlink_id tel email
    private String accountType;
    //帐号
    private String account;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
