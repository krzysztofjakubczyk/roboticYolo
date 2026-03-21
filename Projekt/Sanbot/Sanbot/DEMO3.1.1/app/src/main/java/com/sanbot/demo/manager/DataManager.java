package com.sanbot.demo.manager;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.sanbot.demo.app.start.main.chat.video.VideoActivity;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.demo.entity.Message;
import com.sanbot.demo.util.DateUtil;
import com.sanbot.net.ChatMessage;
import com.sanbot.net.NetApi;
import com.sanbot.net.NetInfo;
import com.sanbot.net.ShowRecvListener;

import java.util.Date;

/**
 * 数据管理
 *
 * @author youngbin
 */
public class DataManager implements ShowRecvListener {

    private static final String TAG = "DataManager";

    private static DataManager mInstance;
    private ChatDBManager mChatDBManager;

    private Context mContext;

    public static DataManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                mInstance = new DataManager(context);
            }
        }
        return mInstance;
    }

    private DataManager(Context context) {
        mContext = context;
        NetApi.getInstance().setrListener(this);
        mChatDBManager = new ChatDBManager(DBHelper.getInstance(context));

    }

    @Override
    public void showRecv(int cmd, int result, Object obj, long seq) {
        Log.i(TAG, "showRecv: cmd=" + cmd + ",result=" + result + ",seq=" + seq);
        Intent intent = new Intent(String.valueOf(cmd));
        intent.putExtra("response", new JniResponse(cmd, result, obj, seq));
        switch (cmd) {
            case NetInfo.QHC_CMD_LOGIN_RSP:
                //登录回调，登录出错，停止登录
                if (result != 0) {
                    NetApi.getInstance().stopLogin();
                }
                break;
            case NetInfo.QHC_CMD_RECV_CHAT_MSG: {
                //收到消息，保存数据库
                Message message = saveChatMessage(obj);
                if (message != null && NetApi.getInstance().getLoginStatus() == 1) {
                    intent.putExtra("message", message);
                    Log.i(TAG, "showRecv: message=" + message.toString());
                    switch (message.getType()) {
                        case NetInfo.CONTENT_TYPE_LIVE_VIDEO://视频聊天
                        case NetInfo.CONTENT_TYPE_LIVE_AUDIO://语音聊天
                            VideoActivity.startActivity(mContext,
                                    message.getFromId(), message.getData(), VideoActivity.STATE_VIDEO_RESPONSE, message.getType());
                            break;
                    }
                }
                break;
            }
            default:
        }
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    private Message saveChatMessage(Object object) {
        if (object != null && object instanceof ChatMessage) {
            ChatMessage chatMessage = (ChatMessage) object;
            Message message = new Message();
            message.setData(chatMessage.getData());
            long date = chatMessage.getDate();
            message.setDate(date);
            message.setType(chatMessage.getType());
            message.setFromId(chatMessage.getFromId());
            message.setToId(chatMessage.getToId());
            message.setState(Message.STATE_SEND_SUCCESS);
            message.setRead(false);
            String dateText = DateUtil.getText(new Date(date * 1000));
            message.setDateText(dateText);
            if (message.getType() == 0) {
                message.setContent(new String(message.getData()));
            }
            switch (message.getType()) {
                case NetInfo.CONTENT_TYPE_TEXT:
                case NetInfo.CONTENT_TYPE_AUDIO:
                case NetInfo.CONTENT_TYPE_IMAGE:
                    long result = mChatDBManager.update(message);
                    if (result > 0) {
                        message.setId(result);
                    }
                    break;
            }
            return message;
        }
        return null;
    }

}
