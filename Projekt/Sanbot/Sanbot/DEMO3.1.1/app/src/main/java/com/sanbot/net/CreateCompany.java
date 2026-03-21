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
    //代表公司为租赁公司
    public static final int COMPANY_RENT = 0;
    //代表公司为语义公司
    public static final int COMPANY_YUYI = 1;
    //B端语义类型公司
    public static final int YUYI_FOR_B = 0;
    //C端语义类型公司
    public static final int YUYI_FOR_C = 1;
    //语义未激活
    public static final int YUYI_STATUS_DISABLED = 0;
    //语义激活
    public static final int YUYI_STATUS_ENABLED = 1;

    private String name;

    private ArrayList<CompanyRobot> robots = new ArrayList<>();

    private int size;

    private int companyType;

    private int yuyiType;

    private ArrayList<CompanyAdmin> admins = new ArrayList<>();

    private ArrayList<CompanyServer> servers = new ArrayList<>();

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    public int getYuyiType() {
        return yuyiType;
    }

    public void setYuyiType(int yuyiType) {
        this.yuyiType = yuyiType;
    }

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
