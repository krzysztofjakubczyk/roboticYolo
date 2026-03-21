package com.sanbot.net;

import java.util.ArrayList;

/**
 *  描述：创建公司。
 *
 *  name：[IN] 公司名称。
 *
 *  size：[IN] 公司规模。
 *
 *  robot：[IN] 机器人32位机器码。
 *
 *  mgr_list：[IN] 管理员UID列表。
 *
 *  mgr_count：[IN] 管理员数量。
 * Created by admin on 2017/10/25.
 */

public class CreateCompany {
    private String name;

    private ArrayList<CompanyRobot> robots = new ArrayList<>();

    private int size;

    private ArrayList<CompanyAdmin> admins = new ArrayList<>();

    private ArrayList<CompanyServer> servers = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CompanyRobot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<CompanyRobot> robots) {
        this.robots = robots;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<CompanyAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<CompanyAdmin> admins) {
        this.admins = admins;
    }

    public ArrayList<CompanyServer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<CompanyServer> servers) {
        this.servers = servers;
    }
}
