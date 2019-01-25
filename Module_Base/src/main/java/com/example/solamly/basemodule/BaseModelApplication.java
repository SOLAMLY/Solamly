package com.example.solamly.basemodule;

import android.app.Application;
import android.content.Context;

/**
 * @Author SOLAMLY
 * @Date 2019/1/25 14:23
 * @Description:
 */
public class BaseModelApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
