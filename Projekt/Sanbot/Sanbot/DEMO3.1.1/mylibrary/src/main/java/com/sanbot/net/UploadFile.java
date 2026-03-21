package com.sanbot.net;

/**
 * Created by QH on 2017/1/19.
 */
public class UploadFile {
    // 文件类型。0-聊天图片，1-头像图片，10个人文件
    public static final int PICTURE_TYPE = 0;

    public static final int AVATAR_TYPE = 1;

    public static final int PERSONAL_TYPE = 10;
    private int dst_uid;

    private String fileName;

    private int fileType;

    private long fileSize;

    private String md5;

    public int getDst_uid() {
        return dst_uid;
    }

    public void setDst_uid(int dst_uid) {
        this.dst_uid = dst_uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
