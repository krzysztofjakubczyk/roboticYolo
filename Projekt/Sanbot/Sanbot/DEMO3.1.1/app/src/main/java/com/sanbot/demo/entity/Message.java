package com.sanbot.demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Message implements Parcelable{

    public static final int STATE_SENDING = 1;
    public static final int STATE_SEND_SUCCESS = 2;
    public static final int STATE_SEND_ERROR = 3;

    private long id;//数据库保存id
    private int fromId;
    private int toId;
    private byte[] data;
    private long date;
    private int type; //0=文字 1=语音，2=图片
    private int state; //发送状态
    private boolean isRead;

    private String content;
    private String dateText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", data=" + Arrays.toString(data) +
                ", date=" + date +
                ", type=" + type +
                ", state=" + state +
                ", isRead=" + isRead +
                ", content='" + content + '\'' +
                ", dateText='" + dateText + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        public Message createFromParcel(Parcel in) {
            Message message = new Message();
            message.setId(in.readLong());
            message.setFromId(in.readInt());
            message.setToId(in.readInt());

            int length = in.readInt();
            if(length > 0){
                byte[] bytes = new byte[length];
                in.readByteArray(bytes);
                message.setData(bytes);
            }

            message.setDate(in.readLong());
            message.setType(in.readInt());
            message.setState(in.readInt());
            message.setRead(in.readInt() > 0);
            message.setContent(in.readString());
            message.setDateText(in.readString());
            return message;
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(fromId);
        dest.writeInt(toId);

        if(data != null){
            dest.writeInt(data.length);
            dest.writeByteArray(data);
        }else {
            dest.writeInt(0);
        }

        dest.writeLong(date);
        dest.writeInt(type);
        dest.writeInt(state);
        dest.writeInt(isRead ? 1 : 0);
        dest.writeString(content);
        dest.writeString(dateText);
    }
}
