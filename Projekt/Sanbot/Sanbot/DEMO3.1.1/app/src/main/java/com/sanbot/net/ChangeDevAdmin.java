package com.sanbot.net;

/**
 * Created by QH on 2017/1/16.
 */
public class ChangeDevAdmin {
    private String tel;

    private String identify;

    private int dev_uid;

    private int mgr_uid;

    private int new_mgr_uid;

    private int old_mgr_perm;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public int getDev_uid() {
        return dev_uid;
    }

    public void setDev_uid(int dev_uid) {
        this.dev_uid = dev_uid;
    }

    public int getMgr_uid() {
        return mgr_uid;
    }

    public void setMgr_uid(int mgr_uid) {
        this.mgr_uid = mgr_uid;
    }

    public int getNew_mgr_uid() {
        return new_mgr_uid;
    }

    public void setNew_mgr_uid(int new_mgr_uid) {
        this.new_mgr_uid = new_mgr_uid;
    }

    public int getOld_mgr_perm() {
        return old_mgr_perm;
    }

    public void setOld_mgr_perm(int old_mgr_perm) {
        this.old_mgr_perm = old_mgr_perm;
    }
}
