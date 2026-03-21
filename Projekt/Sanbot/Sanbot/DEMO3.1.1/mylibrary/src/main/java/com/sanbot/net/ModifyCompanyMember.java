package com.sanbot.net;

/**
 * *  描述：修改公司成员信息。
 * comp_id：[IN] 公司ID。
 * <p>
 * member_id：[IN] 成员UID。
 * <p>
 * departmentId：[IN] 成员部门ID。
 * <p>
 * permission：[IN] 成员权限。
 * <p>
 * title：职位
 * <p>
 * name：名称。
 * <p>
 * Created by admin on 2017/10/25.
 */

public class ModifyCompanyMember {
    private int companyId;

    private int memberId;

    private int departmentId;

    private int permission;

    private String name;

    private String title;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
}
