package com.sanbot.net;

import java.util.ArrayList;

/**
 * *  描述：查询公司成员信息。
 *
 *  阻塞方式：非阻塞。
 *
 *  companyId：[IN] 公司ID。
 *
 *  members：[IN] 成员UID列表。
 * Created by admin on 2017/10/25.
 */

public class QueryCompanyMember {
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
