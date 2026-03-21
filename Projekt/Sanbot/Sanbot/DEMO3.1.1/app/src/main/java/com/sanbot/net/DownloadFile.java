package com.sanbot.net;

/**
 * Created by QH on 2017/1/19.
 */
public class DownloadFile {
    private long fileId;

    private int fileType;

    private int thumbnails;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(int thumbnails) {
        this.thumbnails = thumbnails;
    }
}
