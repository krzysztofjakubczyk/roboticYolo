package com.sanbot.net;

/**
 * *  描述：修改部门信息。
 *
 *  阻塞方式：非阻塞。
 *
 *  companyId：[IN] 公司ID。
 *
 *  departmentId：[IN] 部门ID。
 *
 *  name：[IN] 部门名称
 *
 *  adminUid：[IN] 部门负责人 只能是部门内部成员

 *
 * Created by admin on 2017/10/25.
 */

public class DepartmentInfo {
    private int companyId;

    private int departmentId;

    private String name;

    private int adminUid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(int adminUid) {
        this.adminUid = adminUid;
    }
}
