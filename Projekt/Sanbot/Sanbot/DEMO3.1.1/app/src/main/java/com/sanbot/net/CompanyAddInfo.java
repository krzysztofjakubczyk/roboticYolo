package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/11/21.
 */

public class CompanyAddInfo {
    private int companyId;

    private ArrayList<CompanyRobot> robots = new ArrayList<>();

    private ArrayList<CompanyServer> servers = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public ArrayList<CompanyRobot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<CompanyRobot> robots) {
        this.robots = robots;
    }

    public ArrayList<CompanyServer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<CompanyServer> servers) {
        this.servers = servers;
    }
}
