package com.sanbot.net;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 服务器信息
 * Created by QHPC on 2016/5/9.
 */
public class ServerInfo {
    //消息服务器
    private String mSmsServer = null;
    //消息服务器端口
    private int mSmsServerPort = 0;
    //P2P服务器
    private String mP2pServer = null;
    //P2P服务器端口
    private int mP2pServerPort = 0;
    //FAQ服务器
    private String mFAQServer = null;
    //FAQ服务器端口
    private int mFAQServerPort = 0;
    //文件服务器
    private String mFileServer = null;
    //文件服务器端口
    private int mFileServerPort = 0;
    //用户UID
    private int mUserId = 0;
    private String mpsServer = null;

    private int mpsServerPort = 0;

    private String addressInfo;

    private String faceAddress;

    private String yuyiAddress;

    public String getMpsServer() {
        return mpsServer;
    }

    public void setMpsServer(String mpsServer) {
        this.mpsServer = mpsServer;
    }

    public int getMpsServerPort() {
        return mpsServerPort;
    }

    public void setMpsServerPort(int mpsServerPort) {
        this.mpsServerPort = mpsServerPort;
    }

    public String getSmsServer() {
        return mSmsServer;
    }

    public void setSmsServer(String mSmsServer) {
        this.mSmsServer = mSmsServer;
    }

    public int getSmsServerPort() {
        return mSmsServerPort;
    }

    public void setSmsServerPort(int mSmsServerPort) {
        this.mSmsServerPort = mSmsServerPort;
    }

    public String getP2pServer() {
        return mP2pServer;
    }

    public void setP2pServer(String mP2pServer) {
        this.mP2pServer = mP2pServer;
    }

    public int getP2pServerPort() {
        return mP2pServerPort;
    }

    public void setP2pServerPort(int mP2pServerPort) {
        this.mP2pServerPort = mP2pServerPort;
    }

    public String getFileServer() {
        return mFileServer;
    }

    public void setFileServer(String mFileServer) {
        this.mFileServer = mFileServer;
    }

    public int getFileServerPort() {
        return mFileServerPort;
    }

    public void setFileServerPort(int mFileServerPort) {
        this.mFileServerPort = mFileServerPort;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getFAQServer() {
        return mFAQServer;
    }

    public void setFAQServer(String mFAQServer) {
        this.mFAQServer = mFAQServer;
    }

    public int getFAQServerPort() {
        return mFAQServerPort;
    }

    public void setFAQServerPort(int mFAQServerPort) {
        this.mFAQServerPort = mFAQServerPort;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public String getFaceAddress() {
        return faceAddress;
    }

    public String getYuyiAddress() {
        return yuyiAddress;
    }

    public void setYuyiAddress(String yuyiAddress) {
        this.yuyiAddress = yuyiAddress;
    }

    public void setFaceAddress(String faceAddress) {
        this.faceAddress = faceAddress;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
        initAddress();
    }

    public void initAddress() {
        if (!TextUtils.isEmpty(addressInfo)) {
            try {
                JSONObject jsonObject = new JSONObject(addressInfo);
                int resultCode = jsonObject.optInt("resultCode", 0);
                if (resultCode == 0) {
                    JSONObject server = jsonObject.optJSONObject("server");
                    String access = server.optString("access");
                    String p2p = server.optString("p2p");
                    String mps_sel_server = server.optString("mps_sel_server");
                    faceAddress = server.optString("face_server");
                    String faq_addr = server.optString("faq_addr");
                    yuyiAddress = server.optString("yuyi_addr");

                    if (access != null) {
                        String tokens[] = access.split(":");
                        mSmsServer = tokens[0];
                        mSmsServerPort = Integer.parseInt(tokens[1]);
                    }

                    if (p2p != null) {
                        String tokens[] = p2p.split(":");
                        mP2pServer = tokens[0];
                        mP2pServerPort = Integer.parseInt(tokens[1]);
                    }

                    if (mps_sel_server != null) {
                        String tokens[] = mps_sel_server.split(":");
                        mpsServer = tokens[0];
                        mpsServerPort = Integer.parseInt(tokens[1]);
                    }

                    if (faq_addr != null) {
                        String tokens[] = faq_addr.split(":");
                        mFAQServer = tokens[0];
                        mFAQServerPort = Integer.parseInt(tokens[1]);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
