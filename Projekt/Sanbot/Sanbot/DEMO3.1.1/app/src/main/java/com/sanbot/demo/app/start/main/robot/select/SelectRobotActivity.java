package com.sanbot.demo.app.start.main.robot.select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.BaseAdapter;
import com.sanbot.demo.app.start.main.friend.FriendAdapter;
import com.sanbot.demo.app.start.main.friend.IFriendView;
import com.sanbot.demo.entity.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 机器人选择页
 * @author youngbin
 */

public class SelectRobotActivity extends BaseActivity implements IFriendView {

    @BindView(R.id.select_robot_rv)
    RecyclerView mRecyclerView;

    private FriendAdapter mAdapter;
    private SelectRobotPresenter mPresenter;

    public static void startActivityForResult(Activity activity, int requestCode){
        Intent intent = new Intent(activity, SelectRobotActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_select_robot);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initReceiver() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(getString(R.string.robot_select));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mPresenter = new SelectRobotPresenter(this, this);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    private BaseAdapter.OnItemClickListener<UserInfo> mItemClickListener = new BaseAdapter.OnItemClickListener<UserInfo>() {
        @Override
        public void onItemClick(View view, int position, UserInfo userInfo) {
            Intent intent = new Intent();
            intent.putExtra("user_info", userInfo);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


    @Override
    public void setAdapter(List<UserInfo> list) {
        if (mAdapter == null) {
            mAdapter = new FriendAdapter(list);
            mAdapter.setOnItemClickListener(mItemClickListener);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setList(list);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.clear();
        super.onDestroy();
    }
}
