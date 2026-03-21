package com.sanbot.demo.app.account.login;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.country.Country;
import com.sanbot.demo.app.country.CountryActivity;
import com.sanbot.demo.app.start.main.MainActivity;
import com.sanbot.demo.app.account.auth.AuthLoginActivity;
import com.sanbot.demo.entity.JniResponse;
import com.sanbot.net.NetInfo;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页
 * @author youngbin
 */

public class LoginActivity extends BaseActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    private static final int REQUEST_COUNTRY = 1;

    @BindView(R.id.login_user_et)
    EditText mUserEt;
    @BindView(R.id.login_password_et)
    EditText mPasswordEt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_country_tv)
    TextView mCountryTv;

    private LoginPresenter mPresenter;
    private Country mCountry;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_LOGIN_RSP));
        filter.addAction(String.valueOf(NetInfo.QHC_CMD_ONLINE_STATUS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mLoginReceiver, filter);
    }

    @Override
    protected void initData(Bundle bundle) {
        setTitle(getString(R.string.auth_login));
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @Override
    public void onSuccess() {
        super.onSuccess();
        showMsg(getString(R.string.auth_login_success));
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public String getUser() {
        return mUserEt.getText().toString();
    }

    @Override
    public void setUser(String user) {
        mUserEt.setText(user);
    }

    @Override
    public String getPassword() {
        return mPasswordEt.getText().toString();
    }

    @Override
    public void setPassword(String password) {
        mPasswordEt.setText(password);
    }

    @Override
    public Country getCountry() {
        return mCountry;
    }


    @OnClick(R.id.login_btn)
    void onLogin() {
        showDialog();
        mPresenter.login();
    }

    @OnClick(R.id.login_country_tv)
    void onSelectCountry() {
        CountryActivity.startActivityForResult(this, REQUEST_COUNTRY);
    }

    private BroadcastReceiver mLoginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            JniResponse jniResponse = intent.getParcelableExtra("response");

            if (String.valueOf(NetInfo.QHC_CMD_LOGIN_RSP).equals(action) && jniResponse != null) {
                switch (jniResponse.getResult()) {
                    case 0:
                        //登录成功
                        break;
                    case NetInfo.QLINK_ACCOUNT_SVR_IMEI_OR_UDID_NOT_MATCH:
                        //需要验证码登录
                        onFailed(getString(R.string.auth_login_by_identify_tip));
                        AuthLoginActivity.startActivity(LoginActivity.this);
                        break;
                    case NetInfo.QLINK_ACCOUNT_SVR_PWD_NOT_MATCH:
                        onFailed(getString(R.string.auth_password_error));
                        break;
                    default:
                        onFailed(getString(R.string.common_unknow_error));
                }
            } else if (String.valueOf(NetInfo.QHC_CMD_ONLINE_STATUS).equals(action)) {
                onSuccess();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_COUNTRY: {
                if (resultCode == RESULT_OK && data != null) {
                    Country country = data.getParcelableExtra("country");
                    if (country != null) {
                        setCountry(country);
                    }
                }
                break;
            }
            default:

        }

    }

    public void setCountry(Country country) {
        mCountry = country;
        if (mCountry != null) {
            mCountryTv.setText(String.format(Locale.getDefault(), "%s +%d", mCountry.getName(), mCountry.getCode()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mLoginReceiver);
    }
}
