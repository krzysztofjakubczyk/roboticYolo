package com.sanbot.net;

import android.text.TextUtils;

/**
 * Created by QHPC on 2016/8/22.
 */
public class DevFriendInfo {
    private int friend_uid;
    private int permission;
    private int perm_externd;
    private String account;
    private String alias;
    private String tel;
    private String birthday;
    private String type;
    private String remarks;
    private int base_version;
    private int remarks_version;
    private String logoUrl;//用户LOGO地址
    private byte[] data;
    private byte[] remarkData;

    public byte[] getRemarkData() {
        return remarkData;
    }

    public void setRemarkData(byte[] remarkData) {
        this.remarkData = remarkData;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getFriend_uid() {
        return friend_uid;
    }

    public void setFriend_uid(int friend_uid) {
        this.friend_uid = friend_uid;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getPerm_externd() {
        return perm_externd;
    }

    public void setPerm_externd(int perm_externd) {
        this.perm_externd = perm_externd;
    }

    public String getAccount() {
        account = account.replaceAll("qlink_id_", "");
        account = account.replaceAll("tel_", "");
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        if (data != null) {
            alias = new String(data);
        }
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        if (remarkData != null) {
            remarks = new String(remarkData);
        }
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getBase_version() {
        return base_version;
    }

    public void setBase_version(int base_version) {
        this.base_version = base_version;
    }

    public int getRemarks_version() {
        return remarks_version;
    }

    public void setRemarks_version(int remarks_version) {
        this.remarks_version = remarks_version;
    }

    public String getLogoUrl() {
        if (logoUrl == null || TextUtils.isEmpty(logoUrl)) {
            logoUrl = "0";
        }
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
