package com.sanbot.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sanbot.util.ScreenUtil;

import com.sanbot.demo.R;


/**
 * 索引(转)
 *
 * @author yun zhang
 *         2016-08-29
 */
public class IndexView extends View {

    public static final String TAG = "IndexView";

    private String[] mLetter = {"#", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private float mItemHeight;
    private float mDensity;
    private Rect mTextRect;

    private OnLetterTouchChangeListener mLetterListener;

    public IndexView(Context context) {
        this(context, null);
    }

    public IndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mWidth = right - left;
            mHeight = bottom - top;
            mItemHeight = (float) mHeight / (float) mLetter.length;
        }

    }

    private void init() {
        mDensity = ScreenUtil.getDensity();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(13 * mDensity);
        mPaint.setColor(getResources().getColor(R.color.colorText));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getTextBounds("A", 0, 1, mTextRect = new Rect());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0, size = mLetter.length; i < size; i++) {
            int y = (int) (mItemHeight * i);
            canvas.drawText(mLetter[i], mWidth / 2f, y + (mItemHeight / 2f) + mTextRect.bottom, mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                changeIndex(event);
                break;
            case MotionEvent.ACTION_MOVE:
                changeIndex(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void changeIndex(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x > 0 && x < mWidth && y > 0 && y < mHeight) {
            int index = (int) (y / mItemHeight);
            if (index >= 0 && index < mLetter.length && mLetterListener != null) {
                mLetterListener.onLetterTouch(mLetter[index]);
            }
        }
    }


    public interface OnLetterTouchChangeListener {
        void onLetterTouch(String text);
    }

    public void setOnLetterTouchChangeListener(OnLetterTouchChangeListener listener) {
        this.mLetterListener = listener;
    }


}
