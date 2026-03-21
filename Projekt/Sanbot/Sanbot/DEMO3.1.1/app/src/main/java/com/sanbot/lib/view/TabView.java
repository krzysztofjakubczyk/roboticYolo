package com.sanbot.lib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanbot.util.ScreenUtil;

import com.sanbot.demo.R;


public class TabView extends LinearLayout {

    private static final String TAG = "TabView";

    private Context mContext;
    private Callback mCallback;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void addText(String[] texts) {
        removeAllViews();
        if (texts == null || texts.length <= 0) {
            Log.i(TAG, "texts not null");
            return;
        }
        for (int i = 0, len = texts.length; i < len; i++) {
            createItemView(i, texts[i]);
        }
    }

    private void createItemView(final int position, final String text) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.item_tab_tv);
        textView.setText(text);

        view.setClickable(true);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    return;
                }
                resetView();
                v.setSelected(true);

                if (mCallback != null)
                    mCallback.onCallback(v, position, text);
            }
        });

        LayoutParams params = new LayoutParams(0, ScreenUtil.dip2px(40), 1);
        if (position == 0) {
            view.setSelected(true);
        }
        addView(view, params);
    }

    private void resetView() {
        int count = getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                getChildAt(i).setSelected(false);
            }
        }
    }

    public void setSelect(int position) {
        int count = getChildCount();
        if (position >= 0 || position < count) {
            resetView();
            getChildAt(position).setSelected(true);
        }
    }


    public interface Callback {
        void onCallback(View view, int position, String text);
    }


    public void setCallback(Callback callback) {
        mCallback = callback;
    }
}
