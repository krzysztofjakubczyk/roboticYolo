package com.sanbot.demo.app.splash;

import android.os.Bundle;
import android.view.WindowManager;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.start.StartActivity;

/**
 * @author youngbin
 *         <p>
 *         启动页， 主要实现数据初始化
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    private SplashPresenter mPresenter;

    @Override
    public void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initReceiver() {

    }

    @Override
    public void initData(Bundle bundle) {
        mPresenter = new SplashPresenter(this, this);
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @Override
    public void onSuccess() {
        super.onSuccess();
        StartActivity.startActivity(SplashActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.close();
    }
}
