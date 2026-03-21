package com.sanbot.net;

import java.util.Arrays;

/**
 * Created by QHPC on 2016/4/19.
 * 聊天消息
 */
public class ChatMessage {
    public static final int VERSION = 1;

    private long seq;                        //消息序号
    private int readstatus = 1;                        //音频是否已读
    private int date;                        //日期
    private int usec;                        //微妙数
    private int fromId;                        //源头用户ID
    private int toId;                        //目标用户ID
    private int encrypt;                    //加密方式 0-base64
    private int type;                        //内容:0=文字，1=语音，2=图片
    private int version = 0;                     //版本号
    private int srcGroupId;                    //源头群组ID
    private int dstGroupId;                    //目标群组ID
    private int evType;                    //使用环境:0-普通聊天,1-企业聊天
    private byte[] stringParam;            //使用环境参数1
    private int intParam;                    //使用环境参数2
    private byte[] data;                    //消息内容

    public ChatMessage(int fromId, int usec, int date, int toId, int type, int encrypt, int srcGroupId, int dstGroupId, int evType,int intParam, byte[] stringParam, byte[] message, long seq, int version) {
        this.fromId = fromId;
        this.usec = usec;
        this.date = date;
        this.toId = toId;
        this.seq = seq;
        this.type = type;
        this.encrypt = encrypt;
        this.data = message;
        this.version = version;
        this.srcGroupId = srcGroupId;
        this.dstGroupId = dstGroupId;
        this.evType = evType;
        this.intParam = intParam;
        this.stringParam = stringParam;
    }

    public int getReadStatus() {
        return readstatus;
    }

    public void setReadStatus(int readstatus) {
        this.readstatus = readstatus;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getUsec() {
        return usec;
    }

    public void setUsec(int usec) {
        this.usec = usec;
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

    public int getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(int encrypt) {
        this.encrypt = encrypt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getVersion() {
        return this.version;
    }

    public int getSrcGroupId() {
        return srcGroupId;
    }

    public void setSrcGroupId(int srcGroupId) {
        this.srcGroupId = srcGroupId;
    }

    public int getDstGroupId() {
        return dstGroupId;
    }

    public int getEvType() {
        return evType;
    }

    public void setEvType(int evType) {
        this.evType = evType;
    }

    public String getStringParam() {
        if (stringParam == null) {
            return "";
        }
        return new String(stringParam);
    }

    public void setStringParam(byte[] stringParam) {
        this.stringParam = stringParam;
    }

    public int getIntParam() {
        return intParam;
    }

    public void setIntParam(int intParam) {
        this.intParam = intParam;
    }

    public void setDstGroupId(int dstGroupId) {
        this.dstGroupId = dstGroupId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "seq=" + seq +
                ", readstatus=" + readstatus +
                ", date=" + date +
                ", usec=" + usec +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", encrypt=" + encrypt +
                ", type=" + type +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
