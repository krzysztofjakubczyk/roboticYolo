package com.sanbot.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/2/14.
 */

public class InviteMembers {
    private int groupId;

    private ArrayList<GroupMember> members = new ArrayList<>();

    public void addMember(GroupMember member) {
        this.members.add(member);
    }

    public void onClearMember() {
        this.members.clear();
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
