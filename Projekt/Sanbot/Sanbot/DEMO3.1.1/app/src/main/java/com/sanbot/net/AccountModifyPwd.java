package com.sanbot.net;

/**
 * @param accountType qlink_id, email, tel
 * @param account 帐号
 * @param old_pwd 旧密码
 * @param new_pwd 新密码
 * Created by QH on 2017/1/16.
 */
public class AccountModifyPwd {
    private String accountType;

    private String account;

    private String old_pwd;

    private  String new_pwd;

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

    public String getOldPassword() {
        return old_pwd;
    }

    public void setOldPassword(String old_pwd) {
        this.old_pwd = old_pwd;
    }

    public String getNewPassword() {
        return new_pwd;
    }

    public void setNewPassword(String new_pwd) {
        this.new_pwd = new_pwd;
    }
}
