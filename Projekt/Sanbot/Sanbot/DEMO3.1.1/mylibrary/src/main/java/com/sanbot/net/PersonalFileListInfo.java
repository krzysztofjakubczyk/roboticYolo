package com.sanbot.net;

import java.util.List;

/**
 * Function:处理私人文件列表返回的结构体
 * Created by xuzhuyun on 2016/9/8.
 * QLINK_COMM_GET_PERSONAL_FILE_LIST
 */

public class PersonalFileListInfo {

    private int size;
    private List<Long> mLongs;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Long> getLongs() {
        return mLongs;
    }

    public void setLongs(List<Long> longs) {
        mLongs = longs;
    }
}
