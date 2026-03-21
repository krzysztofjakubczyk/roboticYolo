package com.sanbot.net;

/**
 * Function:
 * Created by xuzhuyun on 2016/9/9.
 */

public class UploadFileFihishiInfo {
    private long fileId;
    private int uid;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UploadFileFihishiInfo{" +
                "fileId=" + fileId +
                ", uid=" + uid +
                '}';
    }
}
