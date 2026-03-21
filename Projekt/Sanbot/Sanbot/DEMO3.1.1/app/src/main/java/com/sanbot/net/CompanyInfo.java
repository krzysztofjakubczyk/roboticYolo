package com.sanbot.net;

import java.util.ArrayList;

/**
 * companyId 公司ID
 * status 公司运营 0:正常 1:欠费 2:停机
 * companyVersion 信息版本号(本地版本号与服务器版本不一致说明有更新)
 * members 成员UID列表
 * Created by admin on 2017/10/25.
 */

public class CompanyInfo {
    private int companyId;

    private int status;

    private int companyVersion;

    private ArrayList<Integer> members = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCompanyVersion() {
        return companyVersion;
    }

    public void setCompanyVersion(int companyVersion) {
        this.companyVersion = companyVersion;
    }

    public ArrayList<Integer> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Integer> members) {
        this.members = members;
    }
}
