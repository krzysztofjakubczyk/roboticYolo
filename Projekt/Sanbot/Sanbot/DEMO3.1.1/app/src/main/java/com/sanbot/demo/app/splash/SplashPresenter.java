package com.sanbot.demo.app.splash;

import android.content.Context;
import android.content.pm.PackageManager;

import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.IBaseView;
import com.sanbot.demo.entity.Constant;
import com.sanbot.demo.util.AndroidUtil;
import com.sanbot.demo.util.Log;
import com.sanbot.net.NetApi;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author youngbin
 *
 * 启动页presenter
 */
public class SplashPresenter extends BasePresenter {

    private static final String TAG = "SplashPresenter";


    private IBaseView mIBaseView;

    public SplashPresenter(Context context) {
        super(context);
        init();
    }

    public SplashPresenter(Context context, IBaseView iBaseView) {
        this(context);
        mIBaseView = iBaseView;
    }


    private void init() {
        Flowable.timer(1, TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        onInitNetLibParams();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mIBaseView.onSuccess();
                    }
                })
                .subscribe();
    }

    /**
     * 关闭时，登录退出，清空网络库
     */
    protected void close() {
        Flowable.empty()
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        NetApi.getInstance().onLogout();
                        NetApi.getInstance().cleanupLib();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .subscribe();
    }


    /**
     * 网络库初始化
     */
    private void onInitNetLibParams() {
        try {
            //获取id
            int appId = AndroidUtil.getAppStore(mContext);

            String ip = Constant.Service.IP;
            int port = Constant.Service.PORT;

            //app版本
            String currentVersion = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            String deviceId = "d12sd332dsfsdf";
            int result = NetApi.getInstance().initLib(mContext, appId,deviceId , ip, port, currentVersion);
            Log.i(TAG, "初始化,result="+result);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


}
