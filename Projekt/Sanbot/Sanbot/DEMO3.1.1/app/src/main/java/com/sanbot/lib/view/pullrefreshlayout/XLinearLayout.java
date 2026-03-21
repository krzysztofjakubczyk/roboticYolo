package com.sanbot.lib.view.pullrefreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Company：QIHAN TECH
 * Date: 2017/6/30
 * Author：HeChangPeng
 */

public class XLinearLayout extends LinearLayout implements IPull {
    public XLinearLayout(Context context) {
        super(context, null);
    }

    public interface OnScrollChangeListener {
        int onGetScrollY();
    }

    private OnScrollChangeListener mListener;

    public void setScrollChangeListener(OnScrollChangeListener listener) {
        this.mListener = listener;
    }

    public XLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean pullDown() {
        return (mListener != null && mListener.onGetScrollY() == 0);
    }

    @Override
    public boolean pullUp() {
        return false;
    }
}
