package com.sanbot.net;

/**
 * Function:处理上传文件返回的结构体
 * Created by xuzhuyun on 2016/9/2.
 */
public class UploadFileResultInfo {

    public int exist;
    public long fileIds;
    public String uploadUrl;
    public String fileMd5;
    public int fileType;
    public int uid;
    private String req_id;

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public long getFileIds() {
        return fileIds;
    }

    public void setFileIds(long fileId) {
        this.fileIds = fileId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UploadFileResultInfo{" +
                "exist=" + exist +
                ", fileIds=" + fileIds +
                ", uploadUrl='" + uploadUrl + '\'' +
                ", fileMd5='" + fileMd5 + '\'' +
                ", fileType=" + fileType +
                ", uid=" + uid +
                '}';
    }
}
