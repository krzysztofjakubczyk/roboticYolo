package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/5/24.
 */

public class AlarmRecordInfo {
    private int devUid;

    private ArrayList<Long> records = new ArrayList<>();

    public int getDevUid() {
        return devUid;
    }

    public void setDevUid(int devUid) {
        this.devUid = devUid;
    }

    public ArrayList<Long> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Long> records) {
        this.records = records;
    }
}
