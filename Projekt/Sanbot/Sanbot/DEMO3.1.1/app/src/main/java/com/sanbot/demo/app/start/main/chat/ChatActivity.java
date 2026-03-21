package com.sanbot.demo.app.start.main.chat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.common.picture.PictureActivity;
import com.sanbot.demo.entity.Constant;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.demo.entity.Message;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.demo.util.Log;
import com.sanbot.lib.view.pullrefreshlayout.PullRefreshLayout;
import com.sanbot.lib.view.pullrefreshlayout.XRecyclerView;
import com.sanbot.net.ChatMessage;
import com.sanbot.net.DownloadFileResultInfo;
import com.sanbot.net.NetInfo;
import com.sanbot.net.UploadFileFihishiInfo;
import com.sanbot.net.UploadFileResultInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 聊天页
 *
 * @author youngbin
 */

public class ChatActivity extends BaseActivity implements IChatView {

    private static final String TAG = "ChatActivity";

    public static final int REQUEST_PICTURE = 1;

    @BindView(R.id.chat_refresh_layout)
    PullRefreshLayout mRefreshLayout;

    @BindView(R.id.chat_refresh_rv)
    XRecyclerView mRecyclerView;

    @BindView(R.id.chat_content_et)
    EditText mContentEt;

    @BindView(R.id.tab_chat_more_layout)
    LinearLayout mMoreLayout;

    private ChatPresenter mPresenter;
    private UserInfo mToUserInfo;
    private ChatAdapter mAdapter;

    public static void startActivity(Activity activity, UserInfo userInfo) {
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra("user_info", userInfo);
        activity.startActivity(intent);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_chat);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
    }

    @Override
    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_SEND_CHAT_MSG_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_RECV_CHAT_MSG));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_UPLOAD_FILE_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_FINISH_UPLOAD_FILE_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_DOWN_FILE_RSP));
        filter.addAction(Constant.Message.CHAT_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(mChatReceiver, filter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mToUserInfo = intent.getParcelableExtra("user_info");
        if (mToUserInfo != null) {
            if (TextUtils.isEmpty(mToUserInfo.getRemark())) {
                setTitle(mToUserInfo.getNickname());
            } else {
                setTitle(mToUserInfo.getRemark());
            }
        }
        mRecyclerView.setLoad(false);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mPresenter = new ChatPresenter(this, this);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @OnClick(R.id.chat_send_btn)
    void onSend(View view) {
        mPresenter.sendText();
        if (mAdapter != null) {
            mRecyclerView.smoothScrollToPosition(mAdapter.getCount());
        }
    }

    @OnClick(R.id.chat_send_more_btn)
    void onMore(View view) {
        mMoreLayout.setVisibility(mMoreLayout.isShown() ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.tab_chat_more_picture_btn)
    void onPicture(View view) {
        PictureActivity.startActivityForResult(this, REQUEST_PICTURE);
    }

  /*  @OnClick(R.id.tab_chat_more_video_btn)
    void onVideo(View view) {
        mPresenter.onCallRequest();
    }*/

    public PullRefreshLayout.OnRefreshListener mRefreshListener = new PullRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.onRefresh();
        }
    };


    @Override
    public int getCount() {
        return mAdapter != null ? mAdapter.getCount() : 0;
    }

    @Override
    public int getToUid() {
        return mToUserInfo != null ? mToUserInfo.getUid() : -1;
    }

    @Override
    public String getContent() {
        return mContentEt.getText().toString();
    }

    @Override
    public void setContent(String content) {
        mContentEt.setText("");
    }

    @Override
    public void addAdapter(int uid, List<Message> list) {
        mRefreshLayout.stopRefreshAndLoad();
        if (mAdapter == null) {
            mAdapter = new ChatAdapter(uid, mToUserInfo, list);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.scrollToPosition(list != null && !list.isEmpty() ? list.size() - 1 : 0);
        } else {
            List<Message> l = mAdapter.getList();
            if (l == null) {
                l = new ArrayList<>();
                mAdapter.setList(l);
            }
            if (list != null) {

                for (int i = list.size() - 1; i >= 0; i--) {
                    l.add(0, list.get(i));
                }
            }
            mAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(list != null && !list.isEmpty() ? list.size() - 1 : 0);
        }
    }

    @Override
    public void addMessage(int uid, Message message) {
        if (mAdapter == null) {
            List<Message> list = new ArrayList<>();
            list.add(message);
            mAdapter = new ChatAdapter(uid, mToUserInfo, list);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            List<Message> l = mAdapter.getList();
            if (l == null) {
                l = new ArrayList<>();
                mAdapter.setList(l);
            }
            l.add(message);
            mAdapter.notifyDataSetChanged();
        }
        mRecyclerView.scrollToPosition(getCount() - 1);
    }

    @Override
    public void notifyData() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(mAdapter.getCount());
        }
    }

    private BroadcastReceiver mChatReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            JniResponse response = intent.getParcelableExtra("response");
            if (String.valueOf(NetInfo.QHC_CMD_SEND_CHAT_MSG_RSP).equals(action)) {


            } else if (String.valueOf(NetInfo.QHC_CMD_RECV_CHAT_MSG).equals(action) && response.getObj() instanceof ChatMessage) {
                Message message = intent.getParcelableExtra("message");
                mPresenter.recvText(message);
            } else if (String.valueOf(NetInfo.QHC_CMD_UPLOAD_FILE_RSP).equals(action) && response.getObj() instanceof UploadFileResultInfo) {
                UploadFileResultInfo info = (UploadFileResultInfo) response.getObj();
                if (info.getExist() == 0) {
                    Log.i(TAG, "文件不存在");
                    mPresenter.uploadFileFinish(info, response.getSeq());
                } else {
                    Log.i(TAG, "文件存在");
                    mPresenter.sendImage(info.getFileIds(), response.getSeq());
                }
            } else if (String.valueOf(NetInfo.QHC_CMD_FINISH_UPLOAD_FILE_RSP).equals(action) && response.getObj() instanceof UploadFileFihishiInfo) {
                UploadFileFihishiInfo info = (UploadFileFihishiInfo) response.getObj();
                mPresenter.sendImage(info.getFileId(), response.getSeq());
            } else if (String.valueOf(NetInfo.QHC_CMD_DOWN_FILE_RSP).equals(action) && response.getObj() instanceof DownloadFileResultInfo) {
                //noinspection unchecked
                DownloadFileResultInfo info = (DownloadFileResultInfo) response.getObj();
                mPresenter.updateImageUrl(info.getDown_url(), response.getSeq());
            } else if (Constant.Message.CHAT_UPDATE.equals(action)) {
                mPresenter.init();
            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PICTURE:
                if (resultCode == RESULT_OK) {
                    List<String> pictureList = data.getStringArrayListExtra("select_list");
                    if (pictureList != null && !pictureList.isEmpty()) {
                        mPresenter.uploadFile(pictureList.get(0));
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.clear();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mChatReceiver);
        super.onDestroy();
    }
}
