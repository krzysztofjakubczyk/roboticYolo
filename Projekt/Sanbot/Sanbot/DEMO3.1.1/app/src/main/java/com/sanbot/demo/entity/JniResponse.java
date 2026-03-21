package com.sanbot.demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 网络响应
 * @author youngbin
 */
public class JniResponse implements Parcelable {

    //命令
    private int cmd;
    //结果
    private int result;
    //返回数据类型
    private Object obj;
    //操作码
    private long seq;


    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public JniResponse() {
    }

    public JniResponse(int cmd, int result, Object obj, long seq) {
        this.cmd = cmd;
        this.result = result;
        this.obj = obj;
        this.seq = seq;
    }

    public static final Parcelable.Creator<JniResponse> CREATOR = new Parcelable.Creator<JniResponse>() {
        public JniResponse createFromParcel(Parcel in) {
            JniResponse jniResponse = new JniResponse();
            jniResponse.cmd = in.readInt();
            jniResponse.result = in.readInt();
            jniResponse.obj = in.readValue(Object.class.getClassLoader());
            jniResponse.seq = in.readLong();
            return jniResponse;
        }

        public JniResponse[] newArray(int size) {
            return new JniResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cmd);
        dest.writeInt(result);
        dest.writeValue(obj);
        dest.writeLong(seq);
    }
}
