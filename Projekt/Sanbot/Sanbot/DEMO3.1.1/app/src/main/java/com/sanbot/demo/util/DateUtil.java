package com.sanbot.demo.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static DateFormat FORMATALL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static DateFormat FORMATYMD = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static DateFormat FORMATYMD_CHN = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());

    public static DateFormat FORMATYM = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
    public static DateFormat FORMATMD = new SimpleDateFormat("MM-dd", Locale.getDefault());


    /**
     * long 转 String
     */
    public static String formatDate(DateFormat format, Date date) {
        return format.format(date);
    }

    /**
     * String 转化成  long
     */
    public static long getTime(DateFormat format, String date) {
        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * String 转化成  long
     */
    public static Date getDate(DateFormat format, String date) {
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取标准时间
     */
    public static String getText(Date date) {
        return formatDate(FORMATALL, date);
    }

    /**
     * 获取标准时间
     */
    public static long getTime(String time) {
        return getTime(FORMATALL, time);
    }

    /**
     * 获取标准时间
     */
    public static String getText(DateFormat format, Date date) {
        return formatDate(format, date);
    }


    /**
     * 时间转化,过去多长时间
     */
    public static String getTextPast(String text) {
        long time = getTime(text);
        long nowTime = new Date().getTime();
        String s = getTimeText(nowTime - time);
        return TextUtils.isEmpty(s) ? text : s;
    }
    /**
     * 时间转化,过去多长时间
     */
    public static String getTextPast(DateFormat format, String text) {
        long time = getTime(format, text);
        long nowTime = new Date().getTime();
        String s = getTimeText(nowTime - time);
        return TextUtils.isEmpty(s) ? text : s;
    }

    /**
     * 时间 to 字符串
     */
    public static String getTimeText(long time) {
        if (time < 60 * 1000L) {
            return String.format(Locale.getDefault(), "%d秒前", time / 1000);
        } else if (time < 60 * 60 * 1000L) {
            return String.format(Locale.getDefault(), "%d分钟前", time / 1000 / 60);
        } else if (time < 24 * 60 * 60 * 1000L) {
            return String.format(Locale.getDefault(), "%d小时前", time / 1000 / 60 / 60);
        } else if (time < 30 * 24 * 60 * 60 * 1000L) {
            return String.format(Locale.getDefault(), "%d天前", time / 1000 / 60 / 60 / 24);
        } else if (time < 12 * 30 * 24 * 60 * 60 * 1000L) {
            return String.format(Locale.getDefault(), "%d月前", time / 1000 / 60 / 60 / 24 / 30);
        }
        return null;
    }


}
