package com.sanbot.demo.app.start.main.robot;

import android.content.Context;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.manager.VideoManager;
import com.sanbot.demo.manager.model.RobotImp;
import com.sanbot.demo.manager.model.biz.IRobot;
import com.sanbot.demo.util.Log;
import com.sanbot.net.NetInfo;
import com.sanbot.net.RobotCmd;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 机器人presenter
 *
 * @author youngbin
 */

public class RobotPresenter extends BasePresenter {

    private static final String TAG = "RobotPresenter";

    private IRobot mIRobot;
    private IRobotView mIRobotView;
    private VideoManager mVideoManager;
    private long mSessionId;
    private boolean mMoving;
    private int mMoveState;

    public RobotPresenter(Context context) {
        super(context);
    }

    public RobotPresenter(Context context, IRobotView iRobotView) {
        this(context);

        mMoving = false;
        mIRobotView = iRobotView;
        mIRobot = new RobotImp();
        mVideoManager = new VideoManager(mIRobotView.getSurfaceView());
    }

    private void move(final int orientation, final float power) {
        if (mMoveState == orientation && mMoving) {
            return;
        }
        mMoving = true;
        mMoveState = orientation;

        final int uid = mIRobotView.getUid();
        final int body = mIRobotView.getBody();

        if (uid < 0) {
            mIRobotView.onFailed(mContext.getString(R.string.robot_select_tip));
            return;
        }
        if (body < 0) {
            mIRobotView.onFailed(mContext.getString(R.string.robot_select_part_tip));
            return;
        }

        mDisposable.add(Flowable.empty().doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                int result = mIRobot.onRobotMove(uid, body, orientation, power, getSeq(uid));
                Log.i(TAG, "move:result=" + result);
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe());
    }


    public void moveUp(float power) {
        move(RobotCmd.CMD_MOVE_UP, power);
    }

    public void moveLeft(float power) {
        move(RobotCmd.CMD_MOVE_LEFT, power);
    }

    public void moveRight(float power) {
        move(RobotCmd.CMD_MOVE_RIGHT, power);
    }

    public void moveDown(float power) {
        move(RobotCmd.CMD_MOVE_DOWN, power);
    }

    public void moveStop(float power) {
        move(RobotCmd.CMD_MOVE_STOP, power);
    }

    public void moveFinish() {
        mMoving = false;
    }


    public void openVideo() {
        final int uid = mIRobotView.getUid();

        if (uid < 0) {
            mIRobotView.onFailed(mContext.getString(R.string.robot_select_tip));
            return;
        }

        mDisposable.add(Flowable.just(uid)
                .map(new Function<Integer, Long>() {
                    @Override
                    public Long apply(Integer integer) throws Exception {
                        return mIRobot.onOpenVideo(integer, 0, 1, 1);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isFinishing()) {
                            return;
                        }

                        mSessionId = aLong;
                        if (Math.abs(aLong) > 510003) {
                            mVideoManager.openVideo();
                            mIRobotView.setPlayer(mContext.getString(R.string.video_pause));
                        } else {
                            mIRobotView.setPlayer(mContext.getString(R.string.video_play));
                            if (aLong == 0) {
                                mIRobotView.onFailed(mContext.getString(R.string.video_be_used_already));
                            } else if (aLong == NetInfo.VIDEO_CONNECT_OCCUPIED) {
                                mIRobotView.onFailed(mContext.getString(R.string.video_max_connect));
                            } else if (aLong == NetInfo.REMOTE_DEVICE_NOT_ONLINE) {
                                mIRobotView.onFailed(mContext.getString(R.string.video_robot_offline));
                            }
                        }
                        Log.i(TAG, "openVideo:result=" + aLong);
                    }
                }));
    }

    public void closeVideo() {
        mDisposable.add(Flowable.just(mSessionId)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return mIRobot.onCloseVideo(aLong);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (isFinishing())
                            return;
                        mSessionId = 0;
                        mVideoManager.closeVideo();
                        mIRobotView.setPlayer(mContext.getString(R.string.video_play));
                        Log.i(TAG, "closeVideo:integer=" + integer);
                    }
                }));


    }


}
