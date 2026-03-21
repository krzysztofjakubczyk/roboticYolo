package com.sanbot.net;

/**
 * Created by admin on 2017/10/25.
 */

public class Department {
    private int companyId;

    private int id;

    private int size;

    private String name;

    private byte[] nameData;

    private String desc;

    private int adminUid;//部门管理者

    private byte[] descData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public String getDesc() {
        if (descData != null){
            desc = new String(descData);
        }
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(int adminUid) {
        this.adminUid = adminUid;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
