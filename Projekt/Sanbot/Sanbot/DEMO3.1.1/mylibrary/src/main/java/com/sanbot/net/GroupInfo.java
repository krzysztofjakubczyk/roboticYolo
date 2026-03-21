package com.sanbot.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QH on 2017/2/13.
 */

public class GroupInfo {
    private int groupId;

    private int groupOwner;

    private String groupName;

    private String announcement;

    private byte[] groupNameData;

    private byte[] announcementData;

    public byte[] getGroupNameData() {
        return groupNameData;
    }

    public void setGroupNameData(byte[] groupNameData) {
        this.groupNameData = groupNameData;
    }

    public byte[] getAnnoucementData() {
        return announcementData;
    }

    public void setAnnoucementData(byte[] annoucementData) {
        this.announcementData = annoucementData;
    }

    private ArrayList<GroupMember> members = new ArrayList<>();

    public String getGroupName() {
        if (groupNameData != null) {
            groupName = new String(groupNameData);
        }
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAnnouncement() {
        if (announcementData != null) {
            announcement = new String(announcementData);
        }
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

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

    public int getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(int groupOwner) {
        this.groupOwner = groupOwner;
    }

    public void setMembers(ArrayList<GroupMember> members) {
        this.members = members;
    }
}
