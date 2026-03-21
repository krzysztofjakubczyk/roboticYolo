package com.sanbot.net;

/**
 * Created by QH on 2017/2/13.
 */

public class GroupMember {

    private int memberUid;

    private String memberRemarks;

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getMemberUid() {
        return memberUid;
    }

    public void setMemberUid(int memberUid) {
        this.memberUid = memberUid;
    }

    public String getMemberRemarks() {
        if (data != null) {
            memberRemarks = new String(data);
        }
        return memberRemarks;
    }

    public void setMemberRemarks(String memberRemarks) {
        this.memberRemarks = memberRemarks;
    }
}
