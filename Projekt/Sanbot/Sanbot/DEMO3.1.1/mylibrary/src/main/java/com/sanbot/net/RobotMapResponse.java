package com.sanbot.net;

import java.util.List;

/**
 * Company：QIHAN TECH
 * Date: 2017/10/16
 * Author：HeChangPeng
 */

public class RobotMapResponse {
    private int result;
    private int total_count;
    private int version;
    private List<RobotPositionBean> list;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<RobotPositionBean> getList() {
        return list;
    }

    public void setList(List<RobotPositionBean> list) {
        this.list = list;
    }
}
