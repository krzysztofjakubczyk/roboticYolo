package com.sanbot.demo.app.account.login;

import android.content.Context;
import android.text.TextUtils;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.app.country.Country;
import com.sanbot.demo.entity.Constant;
import com.sanbot.demo.manager.model.LoginImp;
import com.sanbot.demo.manager.model.biz.ILogin;
import com.sanbot.demo.util.Log;
import com.sanbot.net.NetInfo;

import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录presenter
 * @author youngbin
 */
public class LoginPresenter extends BasePresenter {

    private static final String TAG = "LoginPresenter";

    private ILoginView mILoginView;
    private ILogin mILogin;

    private LoginPresenter(Context context) {
        super(context);

    }

    public LoginPresenter(Context context, ILoginView iLoginView) {
        this(context);
        mILoginView = iLoginView;
        mILogin = new LoginImp();

        init();
    }

    private void init() {
        mPreference.readSharedPreferences(mContext);
        mILoginView.setUser(mPreference.getValue(Constant.Configure.USER, ""));
        mILoginView.setPassword(mPreference.getValue(Constant.Configure.PASSWORD, ""));
    }


    /**
     * 登录
     */
    public void login() {

        final String user = mILoginView.getUser();
        final String password = mILoginView.getPassword();

        if(TextUtils.isEmpty(user)){
            mILoginView.onFailed(mContext.getString(R.string.auth_account_or_password_empty_tip));
            return;
        }
        if(TextUtils.isEmpty(password)){
            mILoginView.onFailed(mContext.getString(R.string.auth_password_empty_tip));
            return;
        }
        /*if (!StringUtil.checkPhone(user)) {
            mILoginView.onFailed("手机号码不正确");
            return;
        }
*/
        String countryCode = "";
        Country country = mILoginView.getCountry();
        if (country != null){
            countryCode = String.format(Locale.getDefault(), "+%04d", country.getCode());
        }
        mDisposable.add(Flowable.just(countryCode)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String countryCode) throws Exception {
                        return mILogin.login(user, password, countryCode);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i(TAG, "onNext: i=" + integer);
                        switch (integer) {
                            case NetInfo.LOGIN_ERROR_ACCOUNT_NOT_FOUND:
                                mILoginView.onFailed(mContext.getString(R.string.auth_account_not_exist_tip));
                                break;
                            case 0:
                                saveUserInfo();
                                break;
                            default:
                                mILoginView.onFailed(mContext.getString(R.string.common_unknow_error)+"["+integer+"]");
                        }
                    }
                }));
    }


    /**
     * 保存用户和密码
     */
    private void saveUserInfo() {
        mPreference.writeSharedPreferences(mContext);
        mPreference.putValue(Constant.Configure.USER, mILoginView.getUser());
        mPreference.putValue(Constant.Configure.PASSWORD, mILoginView.getPassword());
        mPreference.commit();
    }


}
