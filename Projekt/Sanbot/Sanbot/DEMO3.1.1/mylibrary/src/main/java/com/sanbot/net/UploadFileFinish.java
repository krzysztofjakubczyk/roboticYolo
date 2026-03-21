package com.sanbot.net;

/**
 * Created by QH on 2017/1/19.
 */
public class UploadFileFinish {
    public static final int FLAG_FINISH = 0;
    public static final int FLAG_CANCEL = 1;

    private String md5;

    private int fileType;
    //cancel_flag：[IN] 取消类型标记,0-上传完成，1-取消上传。
    private int cancelFlag;

    private String req_id;

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(int cancelFlag) {
        this.cancelFlag = cancelFlag;
    }
}
