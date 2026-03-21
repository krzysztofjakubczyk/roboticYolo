package com.sanbot.net;

import android.text.TextUtils;

/**
 * Created by QHPC on 2016/10/10.
 * 好友基本信息
 */
public class BaseInfo {
    private int uid;//用户ID

    private String account;//用户帐号

    private String tel;//用户电话

    private String alias;//用户昵称

    private int sex;//用户性别

    private String birthday;//用户生日

    private int height;//用户身高

    private int weight;//用户体重

    private String mail;//用户邮件

    private String logoUrl;//用户LOGO地址

    private String type;//用户类型

    private int permission = 0;

    private int externd = 0;

    private int accountType;//帐号类型 0为普通用户 1为文件传输助手

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public BaseInfo(int uid, String account, /*String tel,*/ String alias, int sex, String birthday, int height, int weight, String mail, String logoUrl, String type, int accountType) {
        this.uid = uid;
        this.account = account;
        //this.tel = tel;
        this.alias = alias;
        this.sex = sex;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.mail = mail;
        this.logoUrl = logoUrl;
        this.type = type;
        this.accountType = accountType;
    }

    public BaseInfo() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAccount() {
        account = account.replaceAll("qlink_id_", "");
        account = account.replaceAll("tel_", "");
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAlias() {
        if (alias == null && data != null) {
            alias = new String(data);
        }
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogoUrl() {
        return TextUtils.isEmpty(logoUrl) ? "0" : logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
               /* ", tel='" + tel + '\'' +*/
                ", alias='" + alias + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", type='" + type + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", accountType=" + accountType +
                ", mail='" + mail + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}