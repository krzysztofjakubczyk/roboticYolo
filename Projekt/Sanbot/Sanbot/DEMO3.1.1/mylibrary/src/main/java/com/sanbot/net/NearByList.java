package com.sanbot.net;

/**
 * Created by admin on 2017/9/25.
 */

public class NearByList {
    public static final int IP_TYPE = 1;

    private static final int GPS_TYPE = 2;

    private static final int ALL_TYPE =3;

    private int uid;
    //1-ip   2-distance 3-all
    private int type;
    //如果type=1,则没有距离
    private int distance;
    //2-安全退出  3-在线  4-离线
    private int status;

    public static int getIpType() {
        return IP_TYPE;
    }

    public static int getGpsType() {
        return GPS_TYPE;
    }

    public static int getAllType() {
        return ALL_TYPE;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NearByList{" +
                "uid=" + uid +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
