package com.sanbot.demo.app.account.register;

import android.content.Context;
import android.text.TextUtils;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.country.Country;
import com.sanbot.demo.manager.model.RegisterImp;
import com.sanbot.demo.manager.model.biz.IRegister;
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
 * 注册presenter
 * @author youngbin
 */
public class RegisterPresenter extends BasePresenter {

    private static final String TAG = "RegisterPresenter";

    private IRegisterView mIRegisterView;
    private IRegister mIRegister;

    public RegisterPresenter(Context context) {
        super(context);
    }

    public RegisterPresenter(Context context, IRegisterView iRegisterView) {
        this(context);

        mIRegisterView = iRegisterView;
        mIRegister = new RegisterImp();
    }

    public void getSmsCode() {

        final String user = mIRegisterView.getUser();
        if (TextUtils.isEmpty(user)) {
            mIRegisterView.onFailed(mContext.getString(R.string.auth_phone_empty_tip));
            return;
        }
      /*  if (!StringUtil.checkPhone(user)) {
            mIRegisterView.onFailed("手机号码不正确");
            return;
        }*/
        String countryCode = getCountryCode();
        mDisposable.add(Flowable.just(countryCode)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String countryCode) throws Exception {
                        return mIRegister.getSmsCode(user, countryCode);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "code:result=" + integer);
                        switch (integer) {
                            case 0:
                                mIRegisterView.showMsg(mContext.getString(R.string.auth_identify_send_success));
                                break;
                            case 410011:
                                mIRegisterView.showMsg(mContext.getString(R.string.auth_account_error_tip));
                                break;
                            case NetInfo.REGISTER_SVR_ALREADY_REGISTER:
                                mIRegisterView.showMsg(mContext.getString(R.string.auth_register_already_tip));
                                break;
                            default:
                                mIRegisterView.showMsg(mContext.getString(R.string.common_unknow_error)+"["+integer+"]");
                        }
                    }
                }));
    }

    public String getCountryCode() {
        String countryCode = "";
        Country country = mIRegisterView.getCountry();
        if (country != null){
            countryCode = String.format(Locale.getDefault(), "+%04d", country.getCode());
        }
        return countryCode;
    }

    public void register() {

        final String user = mIRegisterView.getUser();
        final String password = mIRegisterView.getPassword();
        final String code = mIRegisterView.getSmsCode();

        if (TextUtils.isEmpty(user)) {
            mIRegisterView.onFailed(mContext.getString(R.string.auth_phone_empty_tip));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mIRegisterView.onFailed(mContext.getString(R.string.auth_password_empty_tip));
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mIRegisterView.onFailed(mContext.getString(R.string.auth_identify_empty_tip));
            return;
        }

       /* if (!StringUtil.checkPhone(user)) {
            mIRegisterView.onFailed("手机号码不正确");
            return;
        }*/
        String countryCode = getCountryCode();
        mDisposable.add(Flowable.just(countryCode)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String countryCode) throws Exception {
                        return mIRegister.register(user, password, code, countryCode);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "code:result=" + integer);
                        switch (integer) {
                            case 0:
                                mIRegisterView.showMsg(mContext.getString(R.string.auth_register_done_tip));
                                break;
                            default:
                                mIRegisterView.showMsg(mContext.getString(R.string.common_unknow_error));
                        }
                    }
                }));


    }


}
