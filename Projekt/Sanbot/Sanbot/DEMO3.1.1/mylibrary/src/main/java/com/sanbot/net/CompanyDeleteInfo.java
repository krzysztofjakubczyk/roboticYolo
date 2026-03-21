package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/11/21.
 */

public class CompanyDeleteInfo {
    public static final int DELETE_ROBOT = 1;

    public static final int DELETE_SERVER = 2;

    public static final int DELETE_MEMBER = 3;

    public static final int DELETE_DEPARTMENT = 4;

    private int companyId;

    private int type;

    private ArrayList<Integer> list = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }
}
