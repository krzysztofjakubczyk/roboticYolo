package com.sanbot.net;

/**
 * Created by admin on 2017/2/14.
 */
public class UpdateGroupMemberInfo {
    public final static int GROUP_MEMBER_REMARKS = 1;

    private int groupId;
    //更新类型, 1-member_remarks
    private int type;

    private String value;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
