package com.sanbot.demo.app.start.main.robot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.start.main.robot.select.SelectRobotActivity;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.lib.view.ControlView;
import com.sanbot.net.NetInfo;
import com.sanbot.net.RobotCmd;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 机器人页
 * @author youngbin
 */

public class RobotActivity extends BaseActivity implements IRobotView{

    private static final int REQUEST_SELECT_ROBOT = 1000;


    @BindView(R.id.robot_select_btn)
    Button mSelectBtn;

    @BindView(R.id.robot_player_btn)
    Button mPlayerBtn;

    @BindView(R.id.robot_body_rg)
    RadioGroup mBodyRG;

    @BindView(R.id.robot_video_sv)
    SurfaceView mSurfaceView;

    @BindView(R.id.robot_control_cv)
    ControlView mControlView;

    private RobotPresenter mPresenter;
    private UserInfo mUserInfo;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_robot);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        mControlView.setOnMoveStateListener(mMoveStateListener);
    }

    @Override
    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_CTRL_MSG_RSP));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRobotReceiver, filter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(getString(R.string.main_robot));

        mPresenter = new RobotPresenter(this, this);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    private ControlView.OnMoveStateListener mMoveStateListener = new ControlView.OnMoveStateListener() {
        @Override
        public void moveLeft(float power) {
            mPresenter.moveLeft(power);
        }

        @Override
        public void moveRight(float power) {
            mPresenter.moveRight(power);
        }

        @Override
        public void moveUp(float power) {
            mPresenter.moveUp(power);
        }

        @Override
        public void moveDown(float power) {
            mPresenter.moveDown(power);
        }

        @Override
        public void moveStop(float power) {
            mPresenter.moveStop(power);
        }
    };


    @Override
    public int getUid() {
        return mUserInfo != null ? mUserInfo.getUid() : -1;
    }

    @Override
    public int getBody() {
        mBodyRG.getCheckedRadioButtonId();
        int body;
        switch (mBodyRG.getCheckedRadioButtonId()){
            case R.id.robot_header_rb:
                body = RobotCmd.BODY_HEAD;
                break;
            case R.id.robot_left_hand_rb:
                body = RobotCmd.BODY_LEFT_HAND;
                break;
            case R.id.robot_right_hand_rb:
                body = RobotCmd.BODY_RIGHT_HAND;
                break;
            case R.id.robot_footer_rb:
                body = RobotCmd.BODY_FOOT;
                break;
            default:
                body = RobotCmd.BODY_HEAD;
        }
        return body;
    }

    @Override
    public void setPlayer(String text) {
        mPlayerBtn.setText(text);
    }

    @Override
    public void setSelectRobot(String text) {
        mSelectBtn.setText(text);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    @OnClick(R.id.robot_select_btn)
    void onSelect(){
        SelectRobotActivity.startActivityForResult(this, REQUEST_SELECT_ROBOT);
    }


    @OnClick(R.id.robot_player_btn)
    void onPlayer(View view){
        Button button = (Button) view;

        String text = button.getText().toString();
        if(getString(R.string.common_play).equals(text)){
            mPresenter.openVideo();
        }else{
            mPresenter.closeVideo();
        }
    }


    private BroadcastReceiver mRobotReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            JniResponse response = intent.getParcelableExtra("response");
            if (response == null)
                return;
            if(String.valueOf(NetInfo.QHC_CMD_CTRL_MSG_RSP).equals(action)){
                switch (response.getResult()){
                    case 0:
                        mPresenter.moveFinish();
                        break;
                    case NetInfo.REMOTE_DEVICE_NOT_ONLINE:
                        showMsg(getString(R.string.video_robot_offline));
                        break;
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_SELECT_ROBOT:
                if(resultCode == RESULT_OK && data != null){
                    mUserInfo = data.getParcelableExtra("user_info");
                    if(mUserInfo != null){
                        String remark = mUserInfo.getRemark();
                        if(TextUtils.isEmpty(remark)){
                            setSelectRobot(mUserInfo.getNickname());
                        }else{
                            setSelectRobot(String.format(Locale.getDefault(), "%s(%s)", mUserInfo.getRemark(), mUserInfo.getNickname()));
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.closeVideo();
        mPresenter.clear();
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRobotReceiver);
    }
}
