package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/10/31.
 */

public class AddCompanyRobot {
    private int companyId;

    private ArrayList<RobotInfo> robots = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public ArrayList<RobotInfo> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<RobotInfo> robots) {
        this.robots = robots;
    }
}
