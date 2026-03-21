package com.sanbot.demo.app;

import android.app.Activity;
import android.content.Context;

import com.sanbot.demo.manager.DataManager;
import com.sanbot.demo.util.AndroidUtil;
import com.sanbot.demo.util.SharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author youngbin
 *
 * presenter封装 (对seq简单封装，常用操作工具类引用)
 */
public abstract class BasePresenter {

    public static final String TAG = "BasePresenter";

    protected Context mContext;
    //rxjava事件订阅添加 销毁
    protected CompositeDisposable mDisposable;

    protected DataManager mDataManager;
    //xml数据保存，读取
    protected SharedPreferencesUtil mPreference;
    //用来记录请求seq
    protected Map<Long, Object> mSeqMap;

    public BasePresenter(Context context) {
        mContext = context;
        mDataManager = DataManager.getInstance(context);
        mPreference = SharedPreferencesUtil.getInstance();
        mSeqMap = new HashMap<>();
        mDisposable = new CompositeDisposable();
    }

    /**
     * 是否被销毁
     * @return true already finish
     */
    public boolean isFinishing() {
        return ((Activity) mContext).isFinishing();
    }

    protected long getSeq() {
        return getSeq(0);
    }

    protected long getSeq(Object object) {
        long seq = AndroidUtil.getSEQ();
        mSeqMap.put(seq, object);
        return seq;
    }

    protected Object getObject(long seq){
        if(mSeqMap != null && mSeqMap.containsKey(seq)){
            return mSeqMap.get(seq);
        }
        return null;
    }

    protected void removeKey(long seq){
        if(mSeqMap != null && mSeqMap.containsKey(seq)){
            mSeqMap.remove(seq);
        }
    }


    public void clear(){
        if(mSeqMap != null){
            mSeqMap.clear();
        }

        if(mDisposable != null){
            mDisposable.clear();
        }
    }


}
