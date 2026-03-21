package com.sanbot.demo.app.country;

import android.content.Context;
import android.text.TextUtils;


import com.sanbot.demo.R;
import com.sanbot.demo.app.BasePresenter;
import com.sanbot.demo.util.CountryUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CountryPresenter extends BasePresenter {

    private static final String TAG = "CountryPresenter";

    private ICountryView mICountryView;
    private List<Country> mList;
    private List<Country> mSelectList;

    public CountryPresenter(Context context) {
        super(context);
    }

    public CountryPresenter(Context context, ICountryView iCountryView) {
        this(context);
        mICountryView = iCountryView;
        init();
    }

    private void init() {
        Flowable.just(1)
                .map(new Function<Integer, List<Country>>() {
                    @Override
                    public List<Country> apply(Integer i) throws Exception {
                        List<Country> list = CountryUtil.getList(mContext);
                        if(list != null){
                            Collections.sort(list, new CountryUtil.ComparatorCity());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Country>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(List<Country> countries) {
                        mList = countries;
                    }

                    @Override
                    public void onError(Throwable t) {
                        mICountryView.onFailed(mContext.getString(R.string.qh_loss_of_data));
                    }

                    @Override
                    public void onComplete() {
                        mICountryView.setAdapter(mList);
                        mICountryView.onSuccess();
                    }
                });
    }

    /**
     * 搜索城市
     */
    public void searchCountry(String text) {
        if (TextUtils.isEmpty(text)) {
            mICountryView.setAdapter(mList);
            return;
        }
        if (mSelectList == null) {
            mSelectList = new ArrayList<>();
        }
        mSelectList.clear();
        text = text.toLowerCase();
        if (mList == null) {
            return;
        }
        for (Country country : mList) {
            String name = country.getName();
            int code = country.getCode();
            String pinyin = country.getPinyin();

            if ((name != null && name.toLowerCase().contains(text))
                    ||( String.valueOf(code).contains(text))
                    || ((pinyin != null && pinyin.contains(text)))
                    ) {
                mSelectList.add(country);
            }
        }
        mICountryView.setAdapter(mSelectList);
    }


}
