package com.sanbot.demo.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2017/4/27.
 */

public class JsonUtil {

    public static String jsonP2PServerIP(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        String result = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optString("P2PSvrAddr");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
