package com.sanbot.net;

/**
 * companyId 公司ID
 * departmentId 部门子级id
 * Created by admin on 2017/10/25.
 */

public class DepartmentCreate {
    private int companyId;

    private int departmentId;

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
}
