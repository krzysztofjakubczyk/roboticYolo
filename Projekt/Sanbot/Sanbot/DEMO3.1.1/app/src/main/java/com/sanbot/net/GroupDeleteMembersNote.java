package com.sanbot.net;

import java.util.ArrayList;

/**
 * Created by admin on 2017/4/6.
 */

public class GroupDeleteMembersNote {
    private int groupId;

    private int operUid;

    private ArrayList<Integer> members = new ArrayList<>();

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getOperUid() {
        return operUid;
    }

    public void setOperUid(int operUid) {
        this.operUid = operUid;
    }

    public ArrayList<Integer> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Integer> members) {
        this.members = members;
    }
}
