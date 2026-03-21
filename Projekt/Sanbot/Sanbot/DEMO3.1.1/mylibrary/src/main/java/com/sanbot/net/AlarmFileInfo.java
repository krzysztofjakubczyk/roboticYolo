package com.sanbot.net;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017/6/2.
 * uint64_t file_id;
 uint64_t file_size;
 char file_md5[64];
 char file_name[256];

 char cli_desc[256];
 uint32_t alarm_type;
 uint32_t alarm_start_time;
 uint32_t alarm_end_time;

 */

public class AlarmFileInfo implements Parcelable {

    private long fileId;

    private long fileSize;

    private String md5;

    private String fileName;

    private String description;

    private int alarmType;

    private int startTime;

    private int endTime;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int sartTime) {
        this.startTime = sartTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.fileId);
        dest.writeLong(this.fileSize);
        dest.writeString(this.md5);
        dest.writeString(this.fileName);
        dest.writeString(this.description);
        dest.writeInt(this.alarmType);
        dest.writeInt(this.startTime);
        dest.writeInt(this.endTime);
    }

    public AlarmFileInfo() {
    }

    protected AlarmFileInfo(Parcel in) {
        this.fileId = in.readLong();
        this.fileSize = in.readLong();
        this.md5 = in.readString();
        this.fileName = in.readString();
        this.description = in.readString();
        this.alarmType = in.readInt();
        this.startTime = in.readInt();
        this.endTime = in.readInt();
    }

    public static final Parcelable.Creator<AlarmFileInfo> CREATOR = new Parcelable.Creator<AlarmFileInfo>() {
        @Override
        public AlarmFileInfo createFromParcel(Parcel source) {
            return new AlarmFileInfo(source);
        }

        @Override
        public AlarmFileInfo[] newArray(int size) {
            return new AlarmFileInfo[size];
        }
    };
}
