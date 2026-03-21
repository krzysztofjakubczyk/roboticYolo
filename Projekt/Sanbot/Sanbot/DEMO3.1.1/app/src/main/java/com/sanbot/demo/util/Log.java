package com.sanbot.demo.util;


import android.text.TextUtils;

import com.sanbot.demo.entity.Constant;

public class Log {

    private static final String TAG = Log.class.getSimpleName();


    public static void i(String tag, String msg) {
        if (Constant.DEBUG) {
            if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
                android.util.Log.i(TAG, "tag=" + tag + ",msg=" + msg);
            }else {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                android.util.Log.i(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
                android.util.Log.i(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (Constant.DEBUG) {
            if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
                android.util.Log.e(TAG, "tag=" + tag + ",msg=" + msg);
            }else {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                android.util.Log.i(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
                android.util.Log.e(tag, msg);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (Constant.DEBUG) {
            if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
                android.util.Log.d(TAG, "tag=" + tag + ",msg=" + msg);
            }else {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                android.util.Log.i(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
                android.util.Log.d(tag, msg);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (Constant.DEBUG) {
            if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
                android.util.Log.v(TAG, "tag=" + tag + ",msg=" + msg);
            }else {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                android.util.Log.i(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
                android.util.Log.v(tag, msg);
            }
        }
    }





    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(Log.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}
