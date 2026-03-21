package com.sanbot.demo.app.start.main.robot.select;

import android.content.Context;

import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.start.main.friend.IFriendView;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.demo.manager.model.FriendImp;
import com.sanbot.demo.manager.model.biz.IFriend;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 选择机器人presenter
 * @author youngbin
 */

public class SelectRobotPresenter extends BasePresenter {

    private IFriend mIFriend;
    private IFriendView mIFriendView;
    //本地用户信息
    private List<UserInfo> mUserInfoList;

    public SelectRobotPresenter(Context context) {
        super(context);
    }

    public SelectRobotPresenter(Context context, IFriendView iFriendView) {
        this(context);

        mIFriendView = iFriendView;
        mIFriend = new FriendImp(context);
        init();
    }

    private void init() {
        mDisposable.add(Flowable.empty()
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        mUserInfoList = mIFriend.queryRobot();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mIFriendView.setAdapter(mUserInfoList);
                    }
                })
                .subscribe());
    }


}
