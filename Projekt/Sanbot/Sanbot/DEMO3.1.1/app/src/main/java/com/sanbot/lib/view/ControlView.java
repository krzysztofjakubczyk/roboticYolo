package com.sanbot.lib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sanbot.util.ScreenUtil;
import com.sanbot.demo.R;


/**
 * 方向控制器
 */

public class ControlView extends View {

    private static final String TAG = "ControlView";

    //X轴中心点
    private int mCenterX;
    //Y轴中心点
    private int mCenterY;
    //控制点
    private Bitmap mPointBitmap;
    //外环画笔
    private Paint mPaint = new Paint();
    //外环矩阵
    private RectF mRectF = new RectF();
    //控制点矩阵
    private Rect mBitmapDstRect = new Rect();
    //控制点原图矩阵
    private Rect mBitmapSrcRect = new Rect();
    //控制点半径
    private int mPointLength;
    //外环半径
    private int mLength;
    //外环宽度
    private int mStrokeWidth;

    private OnMoveStateListener mOnMoveStateListener;

    public ControlView(Context context) {
        this(context, null);
    }

    public ControlView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mStrokeWidth = ScreenUtil.dip2px(2);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPointBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_control_point);
        mBitmapSrcRect.set(0, 0, mPointBitmap.getWidth(), mPointBitmap.getHeight());

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            int width = right - left;
            int height = bottom - top;
            mCenterX = width / 2;
            mCenterY = height / 2;
            mLength = (Math.min(width, height) - mStrokeWidth) / 2;
            mRectF.set(mCenterX - mLength, mCenterY - mLength, mCenterX + mLength, mCenterY + mLength);
            mPointLength = mLength / 4;
            mBitmapDstRect.set(mCenterX - mPointLength, mCenterY - mPointLength,
                    mCenterX + mPointLength, mCenterY + mPointLength);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRectF, 0, 360, false, mPaint);
        canvas.drawBitmap(mPointBitmap, mBitmapSrcRect, mBitmapDstRect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                if (!checkPoint(event.getX(), event.getY())) {
//                    return super.onTouchEvent(event);
//                }
//                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_POINTER_DOWN | 0x0100:
                int x = (int) event.getX();
                int y = (int) event.getY();
                movePoint(x, y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                movePoint(mCenterX, mCenterY);
                break;
        }
        return true;
    }

    /**
     * 按下区域是否可以操控， 放大2倍控制区域
     *
     * @param x x轴
     * @param y y轴
     * @return boolean true can
     */
    private boolean checkPoint(float x, float y) {
        int pLength = (int) Math.sqrt(Math.pow(x - mCenterX, 2) + Math.pow(y - mCenterY, 2));
        return (pLength / 2) <= mPointLength;
    }


    /**
     * 移动控制点
     *
     * @param x x轴
     * @param y y轴
     */
    private void movePoint(int x, int y) {
        int x1 = x - mCenterX;
        int y1 = y - mCenterY;
        int pLength = (int) Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        if (pLength > mLength) {
            x = ((x1) * mLength) / pLength + mCenterX;
            y = ((y1) * mLength) / pLength + mCenterY;
            pLength = mLength;
        }
        mBitmapDstRect.set(x - mPointLength, y - mPointLength, x + mPointLength, y + mPointLength);
        invalidate();
        if(mOnMoveStateListener != null){
            judgeListener(x1, y1, pLength);
        }
    }


    private void judgeListener(int x, int y, int length){
        float k = y / (float)x;
        float power =  length / (float)mLength;
        if( power == 0){
            Log.i(TAG, "judgeListener: 停");
            mOnMoveStateListener.moveStop(power);
        }else{
            if( k < -1 || k < 1){
                if(x > 0){
                    Log.i(TAG, "judgeListener: 右");
                    mOnMoveStateListener.moveRight(power);
                }else{
                    Log.i(TAG, "judgeListener: 左");
                    mOnMoveStateListener.moveLeft(power);
                }
            }else{
                if( y > 0){
                    Log.i(TAG, "judgeListener: 下");
                    mOnMoveStateListener.moveDown(power);
                }else{
                    Log.i(TAG, "judgeListener: 上");
                    mOnMoveStateListener.moveUp(power);
                }
            }
        }
        Log.i(TAG, "judgeListener: k="+k+",p="+power);

    }


    public interface OnMoveStateListener{
        void moveLeft(float power);
        void moveRight(float power);
        void moveUp(float power);
        void moveDown(float power);
        void moveStop(float power);
    }

    public void setOnMoveStateListener(OnMoveStateListener listener){
        mOnMoveStateListener = listener;
    }



}
