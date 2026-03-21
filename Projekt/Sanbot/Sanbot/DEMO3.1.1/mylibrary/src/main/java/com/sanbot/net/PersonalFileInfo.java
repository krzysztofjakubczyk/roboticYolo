package com.sanbot.net;

import java.util.List;

/**
 * Function:处理获取私人文件信息应答的结构体
 * Created by xuzhuyun on 2016/9/8.
 * QLINK_COMM_GET_PERSONAL_FILE_INFO
 */

public class PersonalFileInfo {
    private int size;
    private List<FileInfo> mFileInfos;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<FileInfo> getFileInfos() {
        return mFileInfos;
    }

    public void setFileInfos(List<FileInfo> fileInfos) {
        mFileInfos = fileInfos;
    }
}
