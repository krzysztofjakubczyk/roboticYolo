package com.sanbot.demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户类
 * @author youngbin
 */
public class UserInfo implements Parcelable{


    public static final int STATE_NORMAL = 0;//默认
    public static final int STATE_UPDATE = 1;//更新
    public static final int STATE_NEWEAST = 2; //最新

    private int uid;//用户ID
    private String account;//用户帐号
    private String tel;//用户电话
    private String nickname;//用户昵称
    private String remark; //备注
    private int sex;//用户性别
    private String birthday;//用户生日
    private int height;//用户身高
    private int weight;//用户体重
    private String email;//用户邮件
    private long avatarId;//头像id
    private String avatarUrl;//头像url
    private String type;//用户类型
    private int permission;
    private int remarkVersion;
    private int baseVersion;

    private int remarkState;//比较备注状态
    private int baseState;//比较基本状态

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAccount() {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(long avatarId) {
        this.avatarId = avatarId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getRemarkVersion() {
        return remarkVersion;
    }

    public void setRemarkVersion(int remarkVersion) {
        this.remarkVersion = remarkVersion;
    }

    public int getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(int baseVersion) {
        this.baseVersion = baseVersion;
    }

    public int getRemarkState() {
        return remarkState;
    }

    public void setRemarkState(int remarkState) {
        this.remarkState = remarkState;
    }

    public int getBaseState() {
        return baseState;
    }

    public void setBaseState(int baseState) {
        this.baseState = baseState;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", tel='" + tel + '\'' +
                ", nickname='" + nickname + '\'' +
                ", remark='" + remark + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", email='" + email + '\'' +
                ", avatarId=" + avatarId +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", type='" + type + '\'' +
                ", permission=" + permission +
                ", remarkVersion=" + remarkVersion +
                ", baseVersion=" + baseVersion +
                ", remarkState=" + remarkState +
                ", baseState=" + baseState +
                '}';
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        public UserInfo createFromParcel(Parcel in) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUid(in.readInt());
            userInfo.setAccount(in.readString());
            userInfo.setTel(in.readString());
            userInfo.setNickname(in.readString());
            userInfo.setRemark(in.readString());
            userInfo.setSex(in.readInt());
            userInfo.setBirthday(in.readString());
            userInfo.setHeight(in.readInt());
            userInfo.setWeight(in.readInt());
            userInfo.setEmail(in.readString());
            userInfo.setAvatarId(in.readLong());
            userInfo.setAvatarUrl(in.readString());
            userInfo.setType(in.readString());
            userInfo.setPermission(in.readInt());
            userInfo.setRemarkVersion(in.readInt());
            userInfo.setBaseVersion(in.readInt());
            userInfo.setRemarkState(in.readInt());
            userInfo.setBaseState(in.readInt());
            return userInfo;
        }

        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(account);
        dest.writeString(tel);
        dest.writeString(nickname);
        dest.writeString(remark);
        dest.writeInt(sex);
        dest.writeString(birthday);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeLong(avatarId);
        dest.writeString(avatarUrl);
        dest.writeString(type);
        dest.writeInt(permission);
        dest.writeInt(remarkVersion);
        dest.writeInt(baseVersion);
        dest.writeInt(remarkState);
        dest.writeInt(baseState);
    }
}
