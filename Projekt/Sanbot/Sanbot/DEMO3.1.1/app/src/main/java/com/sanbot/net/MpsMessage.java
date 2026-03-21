package com.sanbot.net;

import java.util.Arrays;

/**
 * Created by admin on 2017/7/11.
 * uint32_t seq;						//消息序号
 uint32_t date;						//日期
 uint32_t usec;						//微妙数
 uint32_t src_user_id;				//源头用户ID
 uint32_t dst_user_id;				//目标用户ID
 uint32_t encrypt;					//加密方式 0-base64
 uint32_t type;						//内容:0=文字，1=语音，2=图片
 uint32_t src_group_id;				//源头群组ID
 uint32_t dst_group_id;				//目标群组ID
 uint32_t data_len;					//消息长度
 char* data;							//消息内容
 uint32_t version;					//版本号
 char mps_task_id[64];				//任务序号
 uint32_t mps_flag;					//mps标记，0-默认下载任务，1-弹窗选择是否想下载任务
 */

public class MpsMessage {
    //消息序号
    private int seq;
    //日期
    private int date;
    //微秒数
    private int usec;
    //源头用户ID
    private int srcUid;
    //目标用户ID
    private int dstUid;
    //加密方式 0-base64
    private int encrypt;
    //内容:0=文字，1=语音，2=图片
    private int type;
    //目标群组ID
    private int srcGroupId;
    //目标群组ID
    private int dstGroupId;
    //消息内容
    private byte[] data;
    //版本号
    private int version;
    //任务序号
    private String mpsTaskId;
    //mps标记，0-默认下载任务，1-弹窗选择是否想下载任务
    private int mpsFlag;

    public int getSeq() {
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

    public int getSec() {
        return usec;
    }

    public int getUsec() {
        return usec;
    }

    public void setUsec(int usec) {
        this.usec = usec;
    }

    public int getSrcUid() {
        return srcUid;
    }

    public void setSrcUid(int srcUid) {
        this.srcUid = srcUid;
    }

    public int getDstUid() {
        return dstUid;
    }

    public void setDstUid(int dstUid) {
        this.dstUid = dstUid;
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

    public int getSrcGroupId() {
        return srcGroupId;
    }

    public void setSrcGroupId(int srcGroupId) {
        this.srcGroupId = srcGroupId;
    }

    public int getDstGroupId() {
        return dstGroupId;
    }

    public void setDstGroupId(int dstGroupId) {
        this.dstGroupId = dstGroupId;
    }

    public String getData() {
        String value = "";
        if (data != null) {
            value = new String(data);
        }
        return value;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getMpsTaskId() {
        return mpsTaskId;
    }

    public void setMpsTaskId(String mpsTaskId) {
        this.mpsTaskId = mpsTaskId;
    }

    public int getMpsFlag() {
        return mpsFlag;
    }

    public void setMpsFlag(int mpsFlag) {
        this.mpsFlag = mpsFlag;
    }

    @Override
    public String toString() {
        return "MpsMessage{" +
                "seq=" + seq +
                ", date=" + date +
                ", usec=" + usec +
                ", srcUid=" + srcUid +
                ", dstUid=" + dstUid +
                ", encrypt=" + encrypt +
                ", type=" + type +
                ", srcGroupId=" + srcGroupId +
                ", dstGroupId=" + dstGroupId +
                ", data=" + Arrays.toString(data) +
                ", version=" + version +
                ", mpsTaskId='" + mpsTaskId + '\'' +
                ", mpsFlag=" + mpsFlag +
                '}';
    }
}
