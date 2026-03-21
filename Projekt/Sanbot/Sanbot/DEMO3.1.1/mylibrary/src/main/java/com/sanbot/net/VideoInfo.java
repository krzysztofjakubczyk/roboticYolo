package com.sanbot.net;

/**
 * * video->dev_id 设备UID
 * video->channel 视频通道 0
 * video->type 码流类型 0
 * video->stream_contents 0-音视频、1-视频、2-音频
 * companyMode 企业通讯模式 0为非企业 1为企业
 * Created by QH on 2017/1/16.
 */
public class VideoInfo {
    private int h264Data = 0;

    private int channel;

    private int type;

    private int dev_id;
    //stream_contents：[IN] 数据流内容:0-音视频、1-视频、2-音频
    private int stream_contents;
    //连接类型 0 为直连 1 为P2P连接
    private int connectType = 0;

    private int companyMode = 0;//企业通讯模式 0为非企业 1为企业

    private int companyId;//企业id

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public int getStreamContents() {
        return stream_contents;
    }

    public int getConnectType() {
        return connectType;
    }

    public void setConnectType(int connectType) {
        this.connectType = connectType;
    }

    /**
     *  stream_contents 0-音视频、1-视频、2-音频
     * @param stream_contents
     */
    public void setStreamContents(int stream_contents) {
        this.stream_contents = stream_contents;
    }

    public int getStream_contents() {
        return stream_contents;
    }

    public void setStream_contents(int stream_contents) {
        this.stream_contents = stream_contents;
    }

    public int getCompanyMode() {
        return companyMode;
    }

    public void setCompanyMode(int companyMode) {
        this.companyMode = companyMode;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getH264Data() {
        return h264Data;
    }

    public void setH264Data(int h264Data) {
        this.h264Data = h264Data;
    }
}
