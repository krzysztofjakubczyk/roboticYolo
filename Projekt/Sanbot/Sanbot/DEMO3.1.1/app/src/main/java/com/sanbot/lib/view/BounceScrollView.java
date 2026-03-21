package com.sanbot.lib.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.sanbot.util.KeyboardUtil;

/**
 * 可上下移动的scrollView
 */
public class BounceScrollView extends ScrollView {

    private boolean mIsPull; //是否拉动
    private View mChildView; // 子view
    private int mScrollY; // 滑动Y轴距离
    private float mMoveY; // 记录上一次Y坐标
    private int mHeight;
    private Context mContext;

    public BounceScrollView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceScrollView(Context context) {
        this(context, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mChildView = getChildAt(0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHeight = b - t;
        if (mChildView != null) {
            int height = mChildView.getMeasuredHeight();
            mChildView.layout(l, mScrollY, r, mScrollY + height);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mMoveY = ev.getY();
                mIsPull = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //当子控件高度小于父容器，用分发事件来滑动
                if (mChildView != null && mHeight >= mChildView.getHeight()) {
                    scrollY(ev);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                rollbackAnimation();
                break;
            }
            default:
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                if (mChildView != null && mHeight < mChildView.getHeight()) {
                    scrollY(ev);
                }
                break;
            }
            default:
        }
        return mIsPull || super.onTouchEvent(ev);
    }

    private void scrollY(MotionEvent ev) {
        float moveY = ev.getY();
        float y = moveY - mMoveY;
        //移动事件
        if (canPullDown()) {
            mIsPull = 0 < y || mScrollY > 0;
        } else if (canPullUp()) {
            mIsPull = 0 > y || mScrollY < 0;
        }
        if(Math.abs(mMoveY - moveY) > 5){
            if (mIsPull) {
                mScrollY += y / 2;
                requestLayout();
                ev.setAction(MotionEvent.ACTION_CANCEL);
            }
            KeyboardUtil.hideSoftInput((Activity) mContext);
        }
        mMoveY = moveY;
    }

    /**
     * 回滚动画
     */
    private void rollbackAnimation() {
        if (mChildView != null && mScrollY != 0) {
            mChildView.clearAnimation();
            TranslateAnimation animation = new TranslateAnimation(0, 0,
                    mScrollY, 0);
            animation.setDuration(200);
            mChildView.startAnimation(animation);
            mScrollY = 0;
            mIsPull = false;
            requestLayout();
        }
    }

    /**
     * 判断下拉
     */
    private boolean canPullDown() {
        return getScrollY() == 0;
    }

    /**
     * 判断上拉
     */
    private boolean canPullUp() {
        return null != mChildView && getScrollY() >= mChildView.getHeight() - getMeasuredHeight();
    }

}
