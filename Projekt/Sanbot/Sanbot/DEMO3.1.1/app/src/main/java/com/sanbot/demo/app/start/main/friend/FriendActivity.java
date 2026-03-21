package com.sanbot.demo.app.start.main.friend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.BaseAdapter;
import com.sanbot.demo.app.start.main.chat.ChatActivity;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.lib.view.pullrefreshlayout.PullRefreshLayout;
import com.sanbot.lib.view.pullrefreshlayout.XRecyclerView;
import com.sanbot.net.BaseInfo;
import com.sanbot.net.DownloadFileResultInfo;
import com.sanbot.net.FriendRemark;
import com.sanbot.net.FriendVersion;
import com.sanbot.net.NetInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 好友列表页
 * @author youngbin
 */
public class FriendActivity extends BaseActivity implements IFriendView {

    @BindView(R.id.friend_refresh_layout)
    PullRefreshLayout mRefreshLayout;

    @BindView(R.id.friend_refresh_rv)
    XRecyclerView mRecyclerView;

    private FriendAdapter mAdapter;
    private FriendPresenter mPresenter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_friend);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
    }

    @Override
    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_GET_FRIEND_VERSION_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_GET_FRIEND_REMARKS_INFO_QUERY_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_GET_FRIEND_BASE_INFO_QUERY_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_DOWN_FILE_RSP));
        LocalBroadcastManager.getInstance(this).registerReceiver(mFriendReceiver, filter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(getString(R.string.friend_of_mine));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mPresenter = new FriendPresenter(this, this);
        mRefreshLayout.postRefresh(false);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    private PullRefreshLayout.OnRefreshListener mRefreshListener = new PullRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.onRefresh();
        }
    };

    private BaseAdapter.OnItemClickListener<UserInfo> mItemClickListener = new BaseAdapter.OnItemClickListener<UserInfo>() {
        @Override
        public void onItemClick(View view, int position, UserInfo userInfo) {
            ChatActivity.startActivity(FriendActivity.this, userInfo);
        }
    };


    @Override
    public void setAdapter(List<UserInfo> list) {
        mRefreshLayout.stopRefreshAndLoad();
        if (mAdapter == null) {
            mAdapter = new FriendAdapter(list);
            mAdapter.setOnItemClickListener(mItemClickListener);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setList(list);
        }
    }


    private BroadcastReceiver mFriendReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            JniResponse response = intent.getParcelableExtra("response");
            if (response == null)
                return;

            if (String.valueOf(NetInfo.QHC_CMD_GET_FRIEND_VERSION_RSP).equals(action) && response.getObj() instanceof List) {
                //noinspection unchecked
                mPresenter.compareVersion((List<FriendVersion>) response.getObj());
            } else if (String.valueOf(NetInfo.QHC_CMD_GET_FRIEND_REMARKS_INFO_QUERY_RSP).equals(action) && response.getObj() instanceof List) {
                //noinspection unchecked
                mPresenter.updateRemark((List<FriendRemark>) response.getObj());
            } else if (String.valueOf(NetInfo.QHC_CMD_GET_FRIEND_BASE_INFO_QUERY_RSP).equals(action) && response.getObj() instanceof List) {
                //noinspection unchecked
                mPresenter.updateBase((List<BaseInfo>) response.getObj());
            } else if (String.valueOf(NetInfo.QHC_CMD_DOWN_FILE_RSP).equals(action) && response.getObj() instanceof DownloadFileResultInfo) {
                //noinspection unchecked
                mPresenter.updateAvatarUrl((DownloadFileResultInfo) response.getObj(), response.getSeq());
            }
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mFriendReceiver);

    }

}
