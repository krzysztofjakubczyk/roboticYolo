package com.sanbot.demo.app.country;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.demo.app.BaseAdapter;
import com.sanbot.demo.util.Log;
import com.sanbot.lib.view.ClearEditText;
import com.sanbot.lib.view.IndexView;

import java.util.List;

/**
 * 选择国家
 */
public class CountryActivity extends BaseActivity implements ICountryView{

    private static final String TAG = "CountryActivity";

    private ClearEditText mSearchEt;
    private RecyclerView mRecyclerView;
    private IndexView mLetterView;

    private CountryAdapter mAdapter;
    private CountryPresenter mPresenter;
    private LinearLayoutManager mManager;

    public static void startActivityForResult(Activity activity, int requestCode){
        Intent intent = new Intent(activity, CountryActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Fragment fragment, int requestCode){
        Intent intent = new Intent(fragment.getActivity(), CountryActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_country);
        mSearchEt = (ClearEditText) findViewById(R.id.country_search_et);
        mRecyclerView = (RecyclerView) findViewById(R.id.country_rv);
        mLetterView = (IndexView) findViewById(R.id.country_letter_iv);
    }

    @Override
    protected void initListener() {
        mSearchEt.addTextChangedListener(mTextWatcher);
        mLetterView.setOnLetterTouchChangeListener(mLetterListener);
    }

    @Override
    protected void initReceiver() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(R.string.qh_select_country);
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        mPresenter = new CountryPresenter(this, this);
        showDialog();
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    private IndexView.OnLetterTouchChangeListener mLetterListener = new IndexView.OnLetterTouchChangeListener() {
        @Override
        public void onLetterTouch(String text) {
            if(mAdapter != null && mManager != null){
                int position = mAdapter.StringToPosition(text);
                if(position < 0){
                    return;
                }
                mManager.scrollToPositionWithOffset(position, 0);
                mManager.setStackFromEnd(true);
            }
        }
    };

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.searchCountry(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private BaseAdapter.OnItemClickListener<Country> mItemListener = new BaseAdapter.OnItemClickListener<Country>() {
        @Override
        public void onItemClick(View view, int position, Country country) {
            Log.i(TAG, "name="+country.getName());
            Intent intent = new Intent();
            intent.putExtra("country", country);
            setResult(RESULT_OK, intent);
            finish();
        }
    };



    @Override
    public void setAdapter(List<Country> list) {
        if(mAdapter == null){
            mAdapter = new CountryAdapter(list);
            mAdapter.setOnItemClickListener(mItemListener);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setList(list);
        }
    }

    @Override
    public void onSuccess() {
        super.onSuccess();
    }

    @Override
    public void onFailed(String errorMsg) {
        super.onFailed(errorMsg);
    }
}
