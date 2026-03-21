package com.sanbot.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/6.
 */

public class GroupAddMemberNote {
    private int groupId;

    private int operUid;

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

    public void setMembers(ArrayList<GroupMember> members) {
        this.members = members;
    }

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
}
