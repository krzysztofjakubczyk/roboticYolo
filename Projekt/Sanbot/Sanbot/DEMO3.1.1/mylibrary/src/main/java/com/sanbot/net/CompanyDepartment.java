package com.sanbot.net;

/**
 * *  描述：创建部门。
 *
 *  companyId：[IN] 公司ID。
 *
 *  superior_dept_id：[IN] 上级部门ID。
 *
 *  mgr_uid：[IN] 管理者UID。
 *
 *  dept_name：[IN] 部门名称。
 *
 *  size：[IN] 部门规模。
 *
 *  desc：[IN] 部门描述。
 * Created by admin on 2017/10/25.
 */

public class CompanyDepartment {
    private int companyId;

    private String name;

    private String desc;

    private int size;

    private int adminUid;

    private int superior_dept_id;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(int adminUid) {
        this.adminUid = adminUid;
    }

    public int getSuperior_dept_id() {
        return superior_dept_id;
    }

    public void setSuperior_dept_id(int superior_dept_id) {
        this.superior_dept_id = superior_dept_id;
    }
}
