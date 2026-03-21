package com.sanbot.net;

/**
 * Created by admin on 2017/2/14.
 */

public class GroupVersion {
    private int groupId;

    private int groupBaseVersion;

    private int groupMemberVersion;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupBaseVersion() {
        return groupBaseVersion;
    }

    public void setGroupBaseVersion(int groupBaseVersion) {
        this.groupBaseVersion = groupBaseVersion;
    }

    public int getGroupMemberVersion() {
        return groupMemberVersion;
    }

    public void setGroupMemberVersion(int groupMemberVersion) {
        this.groupMemberVersion = groupMemberVersion;
    }
}
