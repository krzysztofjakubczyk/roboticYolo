package com.sanbot.net;

import java.util.ArrayList;

/**
 * companyId 公司ID
 * size 公司规模
 * permission 查询者权限
 * backupUser 常用联系人
 * name 公司名称
 * title 职位
 * logoUrl 公司logo
 * robots 机器人列表
 * admins 管理员列表
 * departments 成员UID列表
 * Created by admin on 2017/10/25.
 */

public class CompanyBaseInfo {
    private int companyId;

    private int size;

    private int permission;

    private String name;

    private byte[] nameData;

    private String logoUrl;
    //公司类型，<=0 代表公司为租赁公司，必须包含人脸服务器，机器人，type=1 代表公司为语义公司，不需要包含人脸
    private int companyType;
    //0-B端语义类型公司 1-C端语义类型公司
    private int yuyiType;
    //语义状态 0-未激活 1-未激活
    private int yuyiStatus;
    //脸状态 0-未激活 1-未激活
    private int faceStatus;

    private ArrayList<CompanyRobot> robots = new ArrayList<>();

    private ArrayList<CompanyAdmin> admins = new ArrayList<>();

    private ArrayList<Department> departments = new ArrayList<>();

    private ArrayList<CompanyServer> servers = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

   /* public int getBackupUser() {
        return backupUser;
    }

    public void setBackupUser(int backupUser) {
        this.backupUser = backupUser;
    }*/

    public String getName() {
        if (nameData != null) {
            name = new String(nameData);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
*/

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

    public int getYuyiStatus() {
        return yuyiStatus;
    }

    public void setYuyiStatus(int yuyiStatus) {
        this.yuyiStatus = yuyiStatus;
    }

    public int getFaceStatus() {
        return faceStatus;
    }

    public void setFaceStatus(int faceStatus) {
        this.faceStatus = faceStatus;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public ArrayList<CompanyRobot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<CompanyRobot> robots) {
        this.robots = robots;
    }

    public ArrayList<CompanyAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<CompanyAdmin> admins) {
        this.admins = admins;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<CompanyServer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<CompanyServer> servers) {
        this.servers = servers;
    }
}
