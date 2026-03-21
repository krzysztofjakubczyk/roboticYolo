package com.sanbot.net;

/**
 * Created by Administrator on 2016/8/14.
 */
public class CMDParam {
    //请求索引号
    private long seq;
    //返回命令字
    private int cmd;
    //不回调标记
    private int mark;
    //不回调标记
    private boolean failed = false;
    //参数
    private Object object;
    //参数
    private Object object2;
    //尝试次数
    private int tryCount = 0;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
        if (failed) {
            setTryCount();
        }
    }

    public Object getObject2() {
        return object2;
    }

    public void setObject2(Object object2) {
        this.object2 = object2;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount() {
        this.tryCount++;
    }
}
