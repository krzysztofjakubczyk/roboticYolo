package com.sanbot.demo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    public static final String TAG = SharedPreferencesUtil.class.getSimpleName();

    public static final String APP_NAME = "qlink";

    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharePreferences;
    private static SharedPreferencesUtil mInstance;

    private SharedPreferencesUtil() {
    }

    public static SharedPreferencesUtil getInstance() {
        if (null == mInstance) {
            synchronized (SharedPreferencesUtil.class) {
                if (null == mInstance)
                    mInstance = new SharedPreferencesUtil();
            }
        }
        return mInstance;
    }

    public void readSharedPreferences(Context context) {
        mSharePreferences = context.getSharedPreferences(APP_NAME,
                Context.MODE_PRIVATE);
    }

    @SuppressLint("CommitPrefEdits")
    public void writeSharedPreferences(Context context) {
        mEditor = context.getSharedPreferences(APP_NAME,
                Context.MODE_PRIVATE).edit();
    }

    /**
     * 赋值
     */
    public void putValue(String key, int value) {
        mEditor.putInt(key, value);
    }

    public void putValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
    }

    public void putValue(String key, String value) {
        mEditor.putString(key, value);
    }

    public void putValue(String key, long value) {
        mEditor.putLong(key, value);
    }

    public void putValue(String key, float value) {
        mEditor.putFloat(key, value);
    }

    public void commit() {
        mEditor.commit();
    }

    /**
     * 获取
     */

    public int getValue(String key, int defaultInt) {
        return mSharePreferences.getInt(key, defaultInt);
    }

    public boolean getValue(String key, boolean defaultBoolean) {
        return mSharePreferences.getBoolean(key, defaultBoolean);
    }

    public String getValue(String key, String defaultString) {
        return mSharePreferences.getString(key, defaultString);
    }

    public long getValue(String key, long defaultLong) {
        return mSharePreferences.getLong(key, defaultLong);
    }

    public float getValue(String key, float defaultFloat) {
        return mSharePreferences.getFloat(key, defaultFloat);
    }

    /**
     * 移除
     */
    public void removeKey(String key) {
        mEditor.remove(key);
    }


    /**
     * 清除所有数据
     */
    public void clear(Context context) {
        writeSharedPreferences(context);
        mEditor.clear();
        commit();
    }

    /**
     * 查询key是否存在
     */
    public boolean contains(String key){
        return mSharePreferences.contains(key);
    }


}
