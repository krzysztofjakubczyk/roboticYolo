package com.sanbot.demo.app.start.main.friend.detail;

import android.content.Context;

import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.demo.manager.model.FriendDetailImp;
import com.sanbot.demo.manager.model.biz.IFriendDetail;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 好友详情presenter
 * @author youngbin
 */
public class FriendDetailPresenter extends BasePresenter {

    private IFriendDetail mIFriendDetail;
    private IFriendDetailView mIFriendDetailView;

    public FriendDetailPresenter(Context context) {
        super(context);
    }

    public FriendDetailPresenter(Context context, IFriendDetailView iFriendDetailView){
        this(context);
        mIFriendDetailView = iFriendDetailView;
        mIFriendDetail = new FriendDetailImp(context);

        init();

    }

    private void init() {

        mDisposable.add(Flowable.just(mIFriendDetailView.getUid())
                .map(new Function<Integer, UserInfo>() {
                    @Override
                    public UserInfo apply(Integer integer) throws Exception {
                        return mIFriendDetail.queryById(integer);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {
                        mIFriendDetailView.setText(userInfo.toString());
                    }
                }));

    }


    public void setRemark(){

        final String remark = mIFriendDetailView.getRemark();

        mDisposable.add(Flowable.just(mIFriendDetailView.getUid())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer i) throws Exception {
                        return mIFriendDetail.setRemark(i, remark, getSeq(i));
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }));

    }



}
