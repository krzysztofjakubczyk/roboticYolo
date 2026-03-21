package com.sanbot.demo.app.account.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.account.login.LoginPresenter;
import com.sanbot.demo.app.country.Country;
import com.sanbot.demo.app.country.CountryActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册页
 * @author youngbin
 */

public class RegisterActivity extends BaseActivity implements IRegisterView {

    private static final String TAG = "RegisterActivity";

    @BindView(R.id.register_user_et)
    EditText mUserEt;

    @BindView(R.id.register_password_et)
    EditText mPasswordEt;

    @BindView(R.id.register_code_et)
    EditText mCodeEt;

    private RegisterPresenter mPresenter;

    @BindView(R.id.login_country_tv)
    TextView mCountryTv;

    private Country mCountry;

    private static final int REQUEST_COUNTRY = 1;
    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initReceiver() {

    }

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
    protected void initData(Bundle savedInstanceState) {
        setTitle(getString(R.string.auth_register));
        mPresenter = new RegisterPresenter(this, this);
    }

    @OnClick(R.id.login_country_tv)
    void onSelectCountry() {
        CountryActivity.startActivityForResult(this, REQUEST_COUNTRY);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @OnClick(R.id.register_code_btn)
    void onGetCode() {
        mPresenter.getSmsCode();
    }

    @OnClick(R.id.register_btn)
    void onRegister() {
        mPresenter.register();
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
    public String getSmsCode() {
        return mCodeEt.getText().toString();
    }

    @Override
    public void setSmsCode(String code) {
        mCodeEt.setText(code);
    }

    @Override
    public Country getCountry() {
        return mCountry;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }
}
