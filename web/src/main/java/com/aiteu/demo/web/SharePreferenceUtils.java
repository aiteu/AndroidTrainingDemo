package com.aiteu.demo.web;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtils {

    private static SharedPreferences mPref;

    public static SharedPreferences get(Context context) {
        if(mPref == null) {
            mPref = context.getSharedPreferences("common.pref", Context.MODE_PRIVATE);
        }
        return mPref;
    }

    public static void putString(Context context, String key, String value) {
        get(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key){
        return get(context).getString(key, "");
    }
}
