package com.sanbot.net;

/**
 * Created by admin on 2017/10/30.
 */

public class CompanyAdmin {
    private int uid;

    private String name;// 用户名称

    private byte[] nameData;

    private String title;// 职称

    private byte[] titleData;

    private int permission;// 权限级别，1:主管理员， 2:辅助管理员，3:部门管理者，4:部门成员....999:外部成员

    private int version;// 用户版本号

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        if (nameData != null) {
            name = new String(nameData);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        if (titleData != null) {
            title = new String(titleData);
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public byte[] getNameData() {
        return nameData;
    }

    public void setNameData(byte[] nameData) {
        this.nameData = nameData;
    }

    public byte[] getTitleData() {
        return titleData;
    }

    public void setTitleData(byte[] titleData) {
        this.titleData = titleData;
    }
}
