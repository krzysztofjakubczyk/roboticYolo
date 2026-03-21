package com.sanbot.demo.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sanbot.demo.R;
import com.sanbot.demo.util.KeyboardUtil;
import com.sanbot.demo.util.ToastUtil;
import com.sanbot.lib.view.CustomProgressDialog;

import butterknife.Unbinder;

/**
 * fragment封装
 * @author youngbin
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    public static final String TAG = "BaseFragment";

    protected View mBaseView;
    protected LinearLayout mTipLayout;

    protected boolean mPrepared;
    protected boolean mLoad;
    private Dialog mDialog;

    //若使用注解，则结束的时候解绑
    protected Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mBaseView == null) {
            mBaseView = initView(inflater, container);
            mTipLayout = (LinearLayout) mBaseView.findViewById(R.id.tip_layout);
            initListener();
            initReceiver();
        }
        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，
        // 必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup) mBaseView.getParent();
        if (parent != null) {
            parent.removeView(mBaseView);
        }
        mPrepared = true;
        readData(savedInstanceState);
        init();
        return mBaseView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mPrepared && !mLoad) {
            initData();
            mLoad = true;
        }
    }

    private void init() {
        if (mPrepared && getUserVisibleHint() && !mLoad) {
            initData();
            mLoad = true;
        }
    }

    protected void showDialog() {
        if (mDialog == null) {
            mDialog = CustomProgressDialog.create(getContext(), "");
        }
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    protected void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /***
     * 设置添加屏幕的背景透明度
     */
    public void backgroundAlpha(float bgAlpha) {
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    public class PopOnDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initListener();

    /**
     * 初始化广播监听
     */
    protected abstract void initReceiver();

    protected abstract void initData();

    protected abstract void readData(Bundle outState);

    protected abstract void saveData(Bundle outState);


    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
        saveData(outState);
    }


    protected void showToast(CharSequence text) {
        if (!getActivity().isFinishing()) {
            ToastUtil.show(getContext(), text);
        }
    }

    @Override
    public void onSuccess() {
        KeyboardUtil.hideSoftInput(getActivity());
        dismissDialog();
    }

    @Override
    public void onFailed(String errorMsg) {
        dismissDialog();
        showMsg(errorMsg);
    }


    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
