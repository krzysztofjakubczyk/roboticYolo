package com.sanbot.demo.app.start.main;

import android.os.Bundle;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.start.main.friend.FriendActivity;
import com.sanbot.demo.app.start.main.robot.RobotActivity;
import com.sanbot.demo.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页
 *
 * @author youngbin
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
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

    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @OnClick(R.id.main_friend_btn)
    void onFriend() {
        Log.i(TAG, "我的好友");
        startActivity(FriendActivity.class);
    }

    @OnClick(R.id.main_robot_btn)
    void onRobot() {
        Log.i(TAG, "机器人");
        startActivity(RobotActivity.class);
    }
}
