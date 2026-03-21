package com.sanbot.demo.util;


import android.content.Context;
import android.text.TextUtils;

import com.sanbot.demo.QHApplication;
import com.sanbot.demo.R;
import com.sanbot.demo.app.country.Country;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CountryUtil {

    private static final String TAG = "CountryUtil";

    public static List<Country> mCountryList;
    private static ReadWriteLock myLock = new ReentrantReadWriteLock();

    public static synchronized List<Country> getList(Context context) {
        try {
            myLock.readLock().lock();
            if (mCountryList != null && !mCountryList.isEmpty()) {
                return mCountryList;
            }
            try {
                long time = System.currentTimeMillis();
                String[] strings = context.getResources().getStringArray(R.array.country_code_list);
                String areaCodeText = IOUtil.readStringFromRaw(context, R.raw.countryid_sequence);

                JSONObject jsonObject = new JSONObject(areaCodeText);
                JSONObject zhObject = jsonObject.optJSONObject("zh");
                if (zhObject == null) {
                    return null;
                }
                JSONObject areaObject = zhObject.optJSONObject("countries");
                if (areaObject == null) {
                    return null;
                }
                mCountryList = new ArrayList<>();
                String ddd = "";
                String ccc = "";
                for (int i = 0, len = strings.length; i < len; i++) {
                    String s = strings[i];
                    if (TextUtils.isEmpty(s)) {
                        continue;
                    }
                    Country country = new Country();
                    String[] ss = s.split("\\*\\+");
                    if (ss.length > 1) {
                        country.setIndex(i);
                        String name = ss[0];
                        country.setName(name);
                        country.setCode(AppUtil.parseInt(ss[1]));
                        country.setAreaCode(areaObject.optInt(String.valueOf(country.getCode())));
                        String pinyin = Pinyin.getSelling(name);
                        country.setPinyin(pinyin);
                        ddd += country.getCode();
                        ddd += ",";
                        ccc += country.getAreaCode();
                        ccc += ",";

                        if (!TextUtils.isEmpty(pinyin) && pinyin.length() > 0) {
                            country.setLetter(pinyin.substring(0, 1).toUpperCase());
                        }
                        mCountryList.add(country);
                    }
                }
                Log.i(TAG, "数据加载耗时=" + (System.currentTimeMillis() - time));
                return mCountryList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } finally {
            myLock.readLock().unlock();
        }

        return null;
    }

    public static synchronized Country get(int index) {
        getList(QHApplication.getApplication());
        if (mCountryList == null || mCountryList.isEmpty()) {
            return null;
        }

        Iterator<Country> iterator = mCountryList.iterator();
        while(iterator.hasNext()){
            Country country = iterator.next();
            if (country.getIndex() == index) {
                return country;
            }
        }

        return null;
    }

    public static synchronized Country getByPhone(int index) {
        getList(QHApplication.getApplication());
        if (mCountryList == null || mCountryList.isEmpty()) {
            return null;
        }

        Iterator<Country> iterator = mCountryList.iterator();
        while(iterator.hasNext()){
            Country country = iterator.next();
            if (country.getIndex() == index) {
                return country;
            }
        }

        return null;
    }


    public static class ComparatorCity implements Comparator<Country> {

        public int compare(Country lhs, Country rhs) {
            if (lhs == null || rhs == null || lhs.equals(rhs)) {
                return 0;
            }
            String lName = lhs.getPinyin();
            String rName = rhs.getPinyin();
            return lName != null ? lName.compareToIgnoreCase(rName) : 0;
        }
    }


}
