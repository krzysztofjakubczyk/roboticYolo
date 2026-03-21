package com.sanbot.net;

/**
 * Created by QH on 2017/1/16.
 */
public class RemarkInfo {
    private int uid;

    private String remark;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RemarkInfo{" +
                "uid=" + uid +
                ", remark='" + remark + '\'' +
                '}';
    }
}
