package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/10/31.
 */

public class DeleteCompanyDepartment {
    private int companyId;

    private ArrayList<Integer> departments = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public ArrayList<Integer> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Integer> departments) {
        this.departments = departments;
    }
}
