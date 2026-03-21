package com.sanbot.net;

/**
 * Function:处理下载文件返回的结构体
 * Created by xuzhuyun on 2016/9/8.
 */

public class DownloadFileResultInfo {
    private long file_size;
    private String file_md5;
    private String down_url;
    private String file_name;
    private long file_id;
    private int thumbnails;
    private String req_id;

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getFile_md5() {
        return file_md5;
    }

    public void setFile_md5(String file_md5) {
        this.file_md5 = file_md5;
    }

    public String getDown_url() {
        return down_url;
    }

    public void setDown_url(String down_url) {
        this.down_url = down_url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public int getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(int thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    @Override
    public String toString() {
        return "DownloadFileResultInfo{" +
                "file_size=" + file_size +
                ", file_md5='" + file_md5 + '\'' +
                ", down_url='" + down_url + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_id=" + file_id +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
