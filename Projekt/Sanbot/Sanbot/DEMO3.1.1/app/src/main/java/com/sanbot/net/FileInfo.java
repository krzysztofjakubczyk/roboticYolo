package com.sanbot.net;

/**
 * Function:
 * Created by xuzhuyun on 2016/9/8.
 */

public class FileInfo {
    /**
     * typedef struct ql_file_info_
     {
     uint64_t file_id;
     uint64_t file_size;
     char file_md5[64];
     char file_name[256];
     }ql_file_info_t;
     * */
    private long file_id;
    private long file_size;
    private String file_md5;
    private String file_name;

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public String getFile_md5() {
        return file_md5;
    }

    public void setFile_md5(String file_md5) {
        this.file_md5 = file_md5;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
