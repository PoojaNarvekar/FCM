package com.neosoft.fcm_demo.prefrenceUtils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by webwerks1 on 1/12/17.
 */

public class SharedPrefManager {

    private static final String SHARED_PREF__NAME = "fcmsharedprefdemo";
    private static final String KEY_ACESS_TOKEN = "token";

    private static Context mContext;
    private static SharedPrefManager mInstance;


    private SharedPrefManager(Context context) {
        mContext = context;

    }

    public static synchronized SharedPrefManager getmInstance(Context context) {
        if (mInstance == null)
            mInstance = new SharedPrefManager(context);
        return mInstance;
    }

    public Boolean storedToken(String token) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF__NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACESS_TOKEN, token);
        editor.apply();
        return true;

    }

    public String getToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF__NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACESS_TOKEN, null);

    }


}

