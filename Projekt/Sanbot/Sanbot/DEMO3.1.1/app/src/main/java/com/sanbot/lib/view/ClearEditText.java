package com.sanbot.lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;

import com.sanbot.demo.R;


/**
 * 自定义带删除按钮的EditText
 *
 * @author youngbin
 *         2016-08-26
 */
public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements OnFocusChangeListener, TextWatcher {

    public static final String TAG = "ClearEditText";

    private Drawable mClearDrawable;
    private Drawable mClearDrawablePress;
    private boolean hasFocus;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片,
        // 获取图片的顺序是左上右下（0,1,2,3,）
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.icon_delete);
        }
        if (mClearDrawablePress == null) {
            mClearDrawablePress = getResources().getDrawable(R.mipmap.icon_delete_hover);
        }
        if (mClearDrawable != null)
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                    mClearDrawable.getIntrinsicHeight());
        if (mClearDrawablePress != null)
            mClearDrawablePress.setBounds(0, 0, mClearDrawablePress.getIntrinsicWidth(),
                    mClearDrawablePress.getIntrinsicHeight());
        // 默认设置隐藏图标
        setClearIconVisible(false, false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent,action=" + event.getAction());
        if (isFocused()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (isTouchClearDrawable(event) && getText().length() > 0) {
                        setClearIconVisible(true, true);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (getText().length() > 0) {
                        setClearIconVisible(true, false);
                    }
                    if (isTouchClearDrawable(event)) {
                        setText("");
                    }
                    break;
                default:
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean isTouchClearDrawable(MotionEvent event) {
        Drawable rightDrawable = getCompoundDrawables()[2];
        if (rightDrawable != null) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int width = getWidth();
            int right = width - getPaddingRight();
            int left = width - getTotalPaddingRight();
            int height2_ = getHeight() / 2;
            int bitmapHeight2_ = mClearDrawable.getIntrinsicHeight() / 2;
            int top = height2_ - bitmapHeight2_ - 5;
            int bottom = height2_ + bitmapHeight2_ + 5;
            if (x < right && x > left && y > top && y < bottom)
                return true;
        }
        return false;
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }


    /**
     * 当ClearEditText焦点发生变化的时候， 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0, false);
        } else {
            setClearIconVisible(false, false);
        }
    }

    protected void setClearIconVisible(boolean visible, boolean isPress) {
        Drawable rightDrawable;
        if (isPress)
            rightDrawable = visible ? mClearDrawablePress : null;
        else
            rightDrawable = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], rightDrawable, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFocus) {
            setClearIconVisible(s.length() > 0, false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}