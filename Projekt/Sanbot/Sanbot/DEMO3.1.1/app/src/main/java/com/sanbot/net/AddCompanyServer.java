package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/11/7.
 */

public class AddCompanyServer {
    private int companyId;

    private ArrayList<CompanyServer> servers = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public ArrayList<CompanyServer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<CompanyServer> servers) {
        this.servers = servers;
    }
}
