package com.sanbot.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/15.
 */

public class CreateGroupStatus {
    private int groupId;

    private ArrayList<Integer> failList = new ArrayList<>();

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getFailList() {
        return failList;
    }

    public void setFailList(ArrayList<Integer> failList) {
        this.failList = failList;
    }
}
