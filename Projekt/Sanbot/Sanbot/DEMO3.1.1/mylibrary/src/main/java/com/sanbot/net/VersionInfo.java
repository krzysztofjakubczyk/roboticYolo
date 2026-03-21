package com.sanbot.net;

/**
 * Created by QHPC on 2016/7/20.
 */
public class VersionInfo {
    private int uid;

    private String sysVersion;

    private String devMcuTopver;

    private String devMcuBottomVer;

    private String devIdaringVer;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getDevMcuTopver() {
        return devMcuTopver;
    }

    public void setDevMcuTopver(String devMcuTopver) {
        this.devMcuTopver = devMcuTopver;
    }

    public String getDevMcuBottomVer() {
        return devMcuBottomVer;
    }

    public void setDevMcuBottomVer(String devMcuBottomVer) {
        this.devMcuBottomVer = devMcuBottomVer;
    }

    public String getDevIdaringVer() {
        return devIdaringVer;
    }

    public void setDevIdaringVer(String devIdaringVer) {
        this.devIdaringVer = devIdaringVer;
    }
}
