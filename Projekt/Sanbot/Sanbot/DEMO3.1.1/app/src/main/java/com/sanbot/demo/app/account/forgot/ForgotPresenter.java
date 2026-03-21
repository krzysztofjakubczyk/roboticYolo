package com.sanbot.demo.app.account.forgot;

import android.content.Context;
import android.text.TextUtils;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.country.Country;
import com.sanbot.demo.entity.Constant;
import com.sanbot.demo.manager.model.ForgotImp;
import com.sanbot.demo.manager.model.biz.IForgot;
import com.sanbot.demo.util.Log;
import com.sanbot.demo.util.StringUtil;
import com.sanbot.net.NetInfo;

import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author youngbin
 * 忘记密码presenter
 */

public class ForgotPresenter extends BasePresenter {

    private static final String TAG = "ForgotPresenter";

    private IForgotView mIForgotView;
    private IForgot mIForgot;

    public ForgotPresenter(Context context) {
        super(context);
    }

    public ForgotPresenter(Context context, IForgotView iForgotView) {
        this(context);
        mIForgotView = iForgotView;
        mIForgot = new ForgotImp();

        init();
    }

    private void init() {
        mPreference.readSharedPreferences(mContext);
        mIForgotView.setUser(mPreference.getValue(Constant.Configure.USER, ""));
    }

    /**
     * 获取短信验证码
     */
    public void getSmsCode() {

        String user = mIForgotView.getUser();

        if (TextUtils.isEmpty(user)) {
            mIForgotView.onFailed(mContext.getString(R.string.auth_phone_empty_tip));
            return;
        }
        /*if (!StringUtil.checkPhone(user)) {
            mIForgotView.onFailed(mContext.getString(R.string.auth_phone_error_tip));
            return;
        }*/
        mDisposable.add(Flowable.just(user)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return mIForgot.getSmsCode(s, getCountryCode());
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "code:result=" + integer);
                        switch (integer) {
                            case 0:
                                mIForgotView.showMsg(mContext.getString(R.string.auth_identify_send_success));
                                break;
                            case 410011:
                                mIForgotView.showMsg(mContext.getString(R.string.auth_account_error_tip));
                                break;
                            default:
                                mIForgotView.showMsg(mContext.getString(R.string.common_unknow_error)+"["+integer+"]");
                        }
                    }
                }));
    }

    public String getCountryCode() {
        String countryCode = "";
        Country country = mIForgotView.getCountry();
        if (country != null){
            countryCode = String.format(Locale.getDefault(), "+%04d", country.getCode());
        }
        return countryCode;
    }

    /**
     * 重设密码
     */
    public void resetPassword() {

        final String user = mIForgotView.getUser();
        final String password = mIForgotView.getPassword();
        final String code = mIForgotView.getSmsCode();

        if (TextUtils.isEmpty(user)) {
            mIForgotView.onFailed(mContext.getString(R.string.auth_phone_empty_tip));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mIForgotView.onFailed(mContext.getString(R.string.auth_password_empty_tip));
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mIForgotView.onFailed(mContext.getString(R.string.auth_identify_empty_tip));
            return;
        }

       /* if (!StringUtil.checkPhone(user)) {
            mIForgotView.onFailed(mContext.getString(R.string.auth_phone_error_tip));
            return;
        }*/

        mDisposable.add(Flowable.just(1)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer i) throws Exception {
                        return mIForgot.resetPassword(user, password, code,getCountryCode());
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "code:result=" + integer);
                        switch (integer) {
                            case 0:
                                mIForgotView.showMsg(mContext.getString(R.string.auth_update_success));
                                break;
                            case NetInfo.QLINK_ACCOUNT_SVR_IDENTIFY_CODE_NOT_FOUND_OR_OUT_OF_DATE_FAIL:
                                mIForgotView.showMsg(mContext.getString(R.string.auth_identify_error_tip));
                                break;
                            default:
                                mIForgotView.showMsg(mContext.getString(R.string.common_unknow_error));
                        }
                    }
                }));


    }


}
