package com.sanbot.demo.app.start.main.chat;

import android.content.Context;
import android.text.TextUtils;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.start.main.chat.video.VideoActivity;
import com.sanbot.demo.entity.Message;
import com.sanbot.demo.manager.model.ChatImp;
import com.sanbot.demo.manager.model.biz.IChat;
import com.sanbot.demo.util.AndroidUtil;
import com.sanbot.demo.util.AppUtil;
import com.sanbot.demo.util.DateUtil;
import com.sanbot.demo.util.Log;
import com.sanbot.net.NetInfo;
import com.sanbot.net.ServerInfo;
import com.sanbot.net.UploadFileResultInfo;

import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 聊天presenter
 *
 * @author youngbin
 */
public class ChatPresenter extends BasePresenter {

    private IChat mIChat;
    private IChatView mIChatView;
    private int mFromUid;

    public ChatPresenter(Context context) {
        super(context);
    }

    public ChatPresenter(Context context, IChatView iChatView) {
        this(context);
        mIChatView = iChatView;
        mIChat = new ChatImp(context);
        init();
    }

    public void init() {
        onRefresh();
    }

    /**
     * 发送文本
     */
    public void sendText() {
        String content = mIChatView.getContent();
        if (TextUtils.isEmpty(content)) {
            mIChatView.onFailed("内容不能为空");
            return;
        }
        send(content.getBytes(), NetInfo.CONTENT_TYPE_TEXT, getSeq());
    }

    /**
     * 图片发送操作1，生成新的seq。
     * 上传文件路径，获取url
     *
     * @param path file path
     */
    public void uploadFile(final String path) {
        final int toId = mIChatView.getToUid();
        if (TextUtils.isEmpty(path)) {
            mIChatView.onFailed("图片不能为空");
            return;
        }
        mDisposable.add(Flowable.just(path)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        Log.i(TAG, "上传文件,path=" + s);
                        File file = new File(s);
                        int result = -1;
                        if (file.exists()) {
                            int type = 0;//聊天图片
                            result = mIChat.onUploadFile(toId, file.getPath(), type, file.length(), getSeq(s));
                        }
                        return result;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "发送图片，i=" + integer);
                    }
                }));
    }

    /**
     * 图片发送操作2
     * 上传文件数据
     *
     * @param info {@link UploadFileResultInfo}
     * @param seq  {@link ChatPresenter#uploadFile(String)} 返回seq
     */
    public void uploadFileFinish(final UploadFileResultInfo info, final long seq) {

        final String path = (String) getObject(seq);

        if (info == null || TextUtils.isEmpty(path)) {
            mIChatView.onFailed("上传失败，文件不存在");
            return;
        }

        mDisposable.add(Flowable.just(1)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer i) throws Exception {
                        String url = info.getUploadUrl();
                        File file = new File(path);
                        boolean flag = AndroidUtil.uploadFileByHttpPut(url, file.getPath());
                        if (flag) {
                            int result = mIChat.onUploadFileFinish(info.getFileMd5(),
                                    info.getFileType(), file.length(), seq);
                            Log.i(TAG, "uploadFileFinish, result=" + result + ",flag=" + flag);
                        } else {
                            Log.i(TAG, "上传失败");
                        }
                        return 0;
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        sendImage(info.getFileIds(), seq);

                    }
                }));



    }


    /**
     * 图片发送操作3
     * 发送图片
     *
     * @param fileId 文件id
     * @param seq    {@link ChatPresenter#uploadFileFinish(UploadFileResultInfo, long)} 返回seq
     */
    public void sendImage(long fileId, long seq) {
        String text = String.format(Locale.getDefault(), "%d", fileId);
        send(text.getBytes(), NetInfo.CONTENT_TYPE_IMAGE, seq);
    }


    /**
     * 视频聊天
     */
    public void onCallRequest() {
        ServerInfo serverInfo = mIChat.getServerInfo();
        if (serverInfo == null) {
            mIChatView.onFailed("服务器异常,请稍候再试");
            return;
        }
        String p2pIp = serverInfo.getP2pServer();
        int p2pPort = serverInfo.getP2pServerPort();
        if (TextUtils.isEmpty(p2pIp) || p2pPort < 0) {
            mIChatView.onFailed("获取服务器地址异常");
            return;
        }
        String ip = String.format(Locale.getDefault(), "{\"P2PSvrAddr\":\"%s:%d\"}", p2pIp, p2pPort);
        byte[] data = ip.getBytes();
        send(data, NetInfo.CONTENT_TYPE_LIVE_VIDEO, getSeq());
        VideoActivity.startActivity(mContext, mIChatView.getToUid(), data, VideoActivity.STATE_VIDEO_REQUEST, NetInfo.CONTENT_TYPE_LIVE_VIDEO);
    }

    /**
     * 接收文字
     *
     * @param message 文字消息
     */
    public void recvText(final Message message) {
        if (message == null) {
            return;
        }
        addMessage(message);
        imageUrlRequest(message);
        mIChatView.notifyData();
    }

    /**
     * 文件id转url操作1
     *
     * @param message 消息对象
     */
    public void imageUrlRequest(final Message message) {

        if (message == null || message.getType() != 2 || !TextUtils.isEmpty(message.getContent())) {
            Log.i(TAG, "不需要获取图片");
            return;
        }

        mDisposable.add(
                Flowable.just(new String(message.getData()))
                        .map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(String string) throws Exception {
                                long fileId = AppUtil.parseLong(string);
                                if (fileId > 0) {
                                    return mIChat.getUrlById(fileId, getSeq(message));
                                }
                                return -1;
                            }
                        })
                        .subscribeOn(Schedulers.newThread()).subscribe());

    }

    /**
     * 文件id转url操作2
     *
     * @param url 图片url
     * @param seq {@link ChatPresenter#imageUrlRequest(Message)}} 返回seq
     */
    public void updateImageUrl(String url, long seq) {
        if (TextUtils.isEmpty(url) || seq < 0) {
            return;
        }
        Object object = getObject(seq);
        if (object != null && object instanceof Message) {
            Message message = (Message) object;
            message.setContent(url);
            long result = mIChat.update(message);
            if (result > 0) {
                mIChatView.notifyData();
            }
            removeKey(seq);
        }
    }

    /**
     * 发送消息
     *
     * @param data 消息数据
     * @param type 消息类型(0文字1语音2图片)
     * @param seq  seq
     */
    private void send(final byte[] data, int type, final long seq) {
        int toId = mIChatView.getToUid();
        final Message message = new Message();
        Date date = new Date();
        message.setDate(date.getTime() / 1000);
        message.setDateText(DateUtil.getText(date));
        message.setType(type);
        message.setFromId(mFromUid);
        message.setToId(toId);
        message.setState(Message.STATE_SENDING);
        message.setData(data);
        if (type == 0) {
            message.setContent(new String(data));
        } else if (type == 2) {
            //图片
            String path = (String) getObject(seq);
            message.setContent(path);
        }
        addMessage(message);
        mIChatView.notifyData();

        mIChatView.setContent("");
        mDisposable
                .add(Flowable.just(1)
                        .map(new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(Integer i) throws Exception {
                                int length = data.length;
                                long result = mIChat.update(message);
                                message.setId(result);
                                Log.i(TAG, "send1, save sql, result=" + result);
                                return mIChat.onSendMsg(message.getToId(), data, length, message.getType(), seq);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "i=" + integer);
                                if (integer == 0) {
                                    message.setState(Message.STATE_SEND_SUCCESS);
                                } else {
                                    message.setState(Message.STATE_SEND_ERROR);
                                }
                                long result = mIChat.update(message);
                                Log.i(TAG, "send2, save sql, result=" + result);
                                mIChatView.notifyData();
                            }
                        }));
    }


    private void addMessage(Message message) {
        mIChatView.addMessage(mFromUid, message);

    }

    public void onRefresh() {
        final int toId = mIChatView.getToUid();
        mDisposable.add(Flowable
                .just(toId)
                .map(new Function<Integer, List<Message>>() {
                    @Override
                    public List<Message> apply(Integer integer) throws Exception {
                        mFromUid = mIChat.getUid();
                        List<Message> messageList = mIChat.queryById(mFromUid, toId, mIChatView.getCount());
                        return messageList != null ? messageList : new ArrayList<Message>();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Message>>() {
                    @Override
                    public void accept(List<Message> list) throws Exception {
                        for (Message message : list) {
                            imageUrlRequest(message);
                        }
                        mIChatView.addAdapter(mFromUid, list);
                    }
                }));
    }


}
