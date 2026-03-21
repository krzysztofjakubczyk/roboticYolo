package com.sanbot.net;

/**
 * Created by admin on 2017/5/24.
 */
public class AlarmType {
    private int devUid;

    private int alarmType;

    private int startTime;

    private int endTime;

    public int getDevUid() {
        return devUid;
    }

    public void setDevUid(int devUid) {
        this.devUid = devUid;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
