package com.sanbot.demo.app;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.sanbot.demo.entity.Constant;
import com.sanbot.demo.util.KeyboardUtil;
import com.sanbot.demo.util.ToastUtil;
import com.sanbot.lib.view.CustomProgressDialog;

import butterknife.Unbinder;


/**
 * 活动页封装类
 * @author youngbin
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    //基类view
    protected View mBaseView;
    //等待对话框
    private Dialog mDialog;
    //若使用注解，则结束的时候解绑
    protected Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        init(savedInstanceState);
    }

    /**
     * 初始化view，先加载布局, setContentView(int);
     */
    protected abstract void initView();

    /**
     * 初始化view监听
     */
    protected abstract void initListener();

    /**
     * 初始化广播监听
     */
    protected abstract void initReceiver();

    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 保存页面数据
     */
    protected abstract void saveData(Bundle outState);


    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
        saveData(outState);
    }

    private void init(Bundle savedInstanceState) {
        initView();
        mBaseView = getWindow().getDecorView();
        initListener();
        initReceiver();
        initData(savedInstanceState);

        //基类广播监听
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.Message.APP_FINISH);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBaseReceiver, filter);
    }

    /**
     * 显示toast
     *
     * @param text 显示内容
     */
    protected void showToast(CharSequence text) {
        if (!isFinishing()) {
            ToastUtil.show(this, text);
        }
    }

    /**
     * 显示dialog
     */
    protected void showDialog() {
        if (mDialog == null) {
            mDialog = CustomProgressDialog.create(BaseActivity.this, "");
        }
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 隐藏dialog
     */
    protected void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 返回键退出
     */
    public void onBack(View view) {
        KeyboardUtil.hideSoftInput(BaseActivity.this);
        finish();
    }

    @Override
    public void onSuccess() {
        dismissDialog();
        KeyboardUtil.hideSoftInput(BaseActivity.this);
    }

    @Override
    public void onFailed(String errorMsg) {
        dismissDialog();
        KeyboardUtil.hideSoftInput(BaseActivity.this);
        showMsg(errorMsg);
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 基类广播
     */
    private BroadcastReceiver mBaseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constant.Message.APP_FINISH.equals(action)) {
                finish();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        dismissDialog();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBaseReceiver);
    }


}

