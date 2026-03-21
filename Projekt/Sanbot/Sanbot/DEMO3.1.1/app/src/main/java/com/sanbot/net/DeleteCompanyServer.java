package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/11/7.
 */

public class DeleteCompanyServer {
    private int companyId;

    private ArrayList<Integer>  servers = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public ArrayList<Integer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<Integer> servers) {
        this.servers = servers;
    }
}
