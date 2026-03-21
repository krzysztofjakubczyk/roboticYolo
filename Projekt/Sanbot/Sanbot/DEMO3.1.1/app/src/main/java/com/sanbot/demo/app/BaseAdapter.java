package com.sanbot.demo.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * @author youngbin
 * @param <T> 适配器基类参数类型
 *
 * 适用于RecyclerView，封装事件监听， 设置参数和添加参数
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 数据列表
     */
    protected List<T> mList;
    /**
     * 当前上下文
     */
    protected Context mContext;

    public BaseAdapter(List<T> list) {
        this.mList = list;
    }

    /**
     * 设置数据
     * @param list 数组
     */
    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param list 数组
     */
    public void addList(List<T> list) {
        if (list != null && !list.isEmpty()) {
            if (mList == null) {
                mList = new ArrayList<>();
            }
            for (T t : list) {
                mList.add(t);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 获取当前显示数据
     * @return List 数组
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 获取当前数据数量
     * @return 整型
     */
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    /**
     * 获取单个数据
     * @param position 下标
     * @return 一条数据
     */
    public T get(int position){
        return mList != null ? mList.get(position) : null;
    }

    /**
     * 创建view
     * @param parent 父类view
     * @param resId 布局ID
     * @return view
     */
    public View createView(ViewGroup parent, int resId) {
        mContext = parent.getContext();
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public OnItemClickListener<T> mListener;



    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mListener = listener;
    }

    /**
     * item点击事件
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T t);
    }


    public OnClickListener mClickListener;

    public void setOnClickListener(OnClickListener listener) {
        mClickListener = listener;
    }

    /**
     * view点击事件
     */
    public interface OnClickListener {
        void onClick(View view);
    }
}
