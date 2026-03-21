package com.sanbot.net;

import java.util.ArrayList;

/**
 * *  描述：添加公司成员。
 *
 *  companyId：[IN] 公司ID。
 *
 *  departmentId：[IN] 部门ID。
 *
 *  members：[IN] 成员UID列表。
 * Created by admin on 2017/10/25.
 */

public class AddCompanyMember {
    private int companyId;

    private int departmentId;

    private ArrayList<CompanyMember>  members = new ArrayList<>();

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

    public ArrayList<CompanyMember> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<CompanyMember> members) {
        this.members = members;
    }
}
