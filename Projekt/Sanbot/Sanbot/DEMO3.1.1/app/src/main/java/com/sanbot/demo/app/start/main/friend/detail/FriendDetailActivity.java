package com.sanbot.demo.app.start.main.friend.detail;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.net.NetInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author youngbin
 * 好友详情
 */

public class FriendDetailActivity extends BaseActivity implements IFriendDetailView {

    private static final String TAG = "FriendDetailActivity";

    @BindView(R.id.friend_detail_remark_et)
    EditText mRemarkEt;

    @BindView(R.id.friend_detail_update_btn)
    Button mUpdateBtn;

    @BindView(R.id.friend_detail_tv)
    TextView mTextView;

    private int mUid;
    private FriendDetailPresenter mPresenter;


    public static void startActivity(Activity activity, int uid) {
        Intent intent = new Intent(activity, FriendDetailActivity.class);
        intent.putExtra("uid", uid);
        activity.startActivity(intent);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_friend_detail);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_SET_FRIEND_REMARKS_RSP));
        LocalBroadcastManager.getInstance(this).registerReceiver(mFriendDetailReceiver, filter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mUid = intent.getIntExtra("uid", 0);

        mPresenter = new FriendDetailPresenter(this, this);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @OnClick(R.id.friend_detail_update_btn)
    void setRemark() {
        mPresenter.setRemark();
    }

    @Override
    public int getUid() {
        return mUid;
    }

    @Override
    public void setText(String text) {
        mTextView.setText(text);
    }

    @Override
    public String getRemark() {
        return mRemarkEt.getText().toString();
    }

    private BroadcastReceiver mFriendDetailReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            JniResponse response = intent.getParcelableExtra("response");
            if (response == null) {
                return;
            }
            if (String.valueOf(NetInfo.QHC_CMD_SET_FRIEND_REMARKS_RSP).equals(action)) {
                if (response.getResult() == 0) {
                    showMsg("修改成功");
                } else {
                    showMsg("修改失败");
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mFriendDetailReceiver);
    }
}
