package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/10/31.
 */

public class DeleteCompanyMember {
    private int companyId;

    private ArrayList<Integer> members = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public ArrayList<Integer> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Integer> members) {
        this.members = members;
    }
}
