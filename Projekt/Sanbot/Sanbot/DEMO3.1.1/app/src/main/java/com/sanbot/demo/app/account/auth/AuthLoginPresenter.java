package com.sanbot.demo.app.account.auth;

import android.content.Context;
import android.util.Log;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.country.Country;
import com.sanbot.demo.entity.Constant;
import com.sanbot.demo.manager.model.AuthImp;
import com.sanbot.demo.manager.model.biz.IAuth;
import com.sanbot.demo.util.StringUtil;

import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author youngbin
 * 验证码登录Presenter
 */
public class AuthLoginPresenter extends BasePresenter {

    private static final String TAG = "AuthLoginPresenter";

    private IAuthLoginView mIAuthLoginView;
    private IAuth mIAuth;
    private Context mContext;

    public AuthLoginPresenter(Context context) {
        super(context);
    }

    public AuthLoginPresenter(Context context, IAuthLoginView iAuthView) {
        this(context);
        mIAuth = new AuthImp();
        mIAuthLoginView = iAuthView;
        mContext = context;
        init();
    }

    private void init() {
        mPreference.readSharedPreferences(mContext);
        String user = mPreference.getValue(Constant.Configure.USER, "");
        if (StringUtil.checkPhone(user)) {
            mIAuthLoginView.setUser(mPreference.getValue(Constant.Configure.USER, ""));
        }
    }


    /**
     * 获取短信验证码
     */
    public void getSmsCode() {

        String user = mIAuthLoginView.getUser();

        mDisposable.add(Flowable.just(user)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String string) throws Exception {
                        return mIAuth.getSmsCode(string, getCountryCode());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "onNext: i=" + integer);
                        if (integer == 0) {
                            mIAuthLoginView.startTimer();
                            mIAuthLoginView.showMsg(mContext.getString(R.string.auth_identify_send_success));
                        } else {
                            mIAuthLoginView.showMsg(mContext.getString(R.string.auth_identify_send_failed));
                        }
                    }
                }));
    }

    public String getCountryCode() {
        String countryCode = "";
        Country country = mIAuthLoginView.getCountry();
        if (country != null){
            countryCode = String.format(Locale.getDefault(), "+%04d", country.getCode());
        }
        return countryCode;
    }

    /**
     * 通过短信验证码登录
     */
    public void loginBySmsCode() {

        final String tel = mIAuthLoginView.getUser();
        String code = mIAuthLoginView.getSmsCode();

        mDisposable.add(Flowable.just(code)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String string) throws Exception {
                        return mIAuth.loginBySmsCoder(tel, string, getCountryCode());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "onNext: i=" + integer);
                        if (integer == 0) {
                            mIAuthLoginView.showMsg(mContext.getString(R.string.auth_login_success));
                        } else {
                            mIAuthLoginView.showMsg(mContext.getString(R.string.auth_login_failed)+"["+integer+"]");
                        }
                    }
                }));
    }

}
