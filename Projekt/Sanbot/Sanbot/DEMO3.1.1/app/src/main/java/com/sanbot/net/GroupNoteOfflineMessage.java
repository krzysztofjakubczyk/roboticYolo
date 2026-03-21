package com.sanbot.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017/7/11.
 * uint32_t msg_id;
 uint32_t msg_type;// group_note_type
 uint32_t group_id;
 uint32_t date;
 uint32_t oper_uid;//operator uid

 uint32_t oper_type;
 char oper_value[256];

 uint32_t new_mgr_uid;

 qhc_group_member_info_t* member_lst;
 uint32_t lst_num;
 */

public class GroupNoteOfflineMessage {
    public static final int GROUP_NOTE_CREATE 			= 1;
    public static final int GROUP_NOTE_UPDATE_INFO 		= 2;//群组信息更新通知
    public static final int GROUP_NOTE_UPDATE_MGR 		= 3;//群组转移管理员通知
    public static final int GROUP_NOTE_MEMB_INFO_UPDATE = 4;//群组成员信息更新通知
    public static final int GROUP_NOTE_ADD_QRCODE 		= 5;//群组成员通过二维码加入群组通知
    public static final int GROUP_NOTE_ADD_MEMBER 		= 6;//群组邀请成员加入通知
    public static final int GROUP_NOTE_DEL_MEMBER 		= 7;
    public static final int GROUP_NOTE_EXIT_MEMBER 		= 8;

    private int messageId;

    private int messageType;

    private int groupId;

    private int date;

    private int operUid;

    private int operType;

    private byte[] operValue;

    private int newAdmin;

    private ArrayList<GroupMember> members = new ArrayList<>();

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getOperUid() {
        return operUid;
    }

    public void setOperUid(int operUid) {
        this.operUid = operUid;
    }

    public int getOperType() {
        return operType;
    }

    public void setOperType(int operType) {
        this.operType = operType;
    }

    public String getOperValue() {
        String value = "";
        if (operValue != null) {
            value = new String(operValue);
        }
        return value;
    }

   /* public void setOperValue(String operValue) {
        this.operValue = operValue;
    }*/

    public int getNewAdmin() {
        return newAdmin;
    }

    public void setNewAdmin(int newAdmin) {
        this.newAdmin = newAdmin;
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

    public void setMembers(ArrayList<GroupMember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "GroupNoteOfflineMessage{" +
                "messageId=" + messageId +
                ", messageType=" + messageType +
                ", groupId=" + groupId +
                ", date=" + date +
                ", operUid=" + operUid +
                ", operType=" + operType +
                ", operValue=" + Arrays.toString(operValue) +
                ", newAdmin=" + newAdmin +
                ", members=" + members +
                '}';
    }
}
