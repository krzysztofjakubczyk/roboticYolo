package com.sanbot.net;

/**
 * Created by QHPC on 2016/8/22.
 */
public class FamilyMemberInfo {
    private int id;
    private String name;
    private int logoUrl;
    private int sex;
    private String birthday;
    private int qlinkid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(int logoUrl) {
        this.logoUrl = logoUrl;
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

    public int getQlinkid() {
        return qlinkid;
    }

    public void setQlinkid(int qlinkid) {
        this.qlinkid = qlinkid;
    }
}
