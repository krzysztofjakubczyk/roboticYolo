package com.sanbot.net;

/**
 * Created by admin on 2017/6/6.
 */

class FinishDownloadFileInfo {
    private int devUid;

    private int cancelFlag;

    private String req_id;

    public int getDevUid() {
        return devUid;
    }

    public void setDevUid(int devUid) {
        this.devUid = devUid;
    }

    public int getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(int cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }
}
