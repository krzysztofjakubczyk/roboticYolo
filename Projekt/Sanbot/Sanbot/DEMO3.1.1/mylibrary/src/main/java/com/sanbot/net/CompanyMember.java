package com.sanbot.net;
import java.io.Serializable;

/**
 * companyId 公司ID
 * departmentId 部门id 
 * uid 用户UID
 * name 公司名称
 * title 职称
 * tel 电话
 * permission 权限级别，1:主管理员， 2:辅助管理员，3:部门管理者，4:部门成员....999:外部成员
 * Created by admin on 2017/10/25.
 */

public class CompanyMember implements Serializable{
    private int companyId;

    private int departmentId;

    private int uid;

    private String name;

    private String title;

    private int permission;

    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "CompanyMember {" +
                "companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", uid=" + uid +
                ", permission=" + permission +
                ", name=" + name +
                ", title=" + title +"}";
    }
}
