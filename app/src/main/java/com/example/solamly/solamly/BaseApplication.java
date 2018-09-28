package com.example.solamly.solamly;

import android.app.Application;
import android.content.Context;

import com.example.solamly.solamly.module.greendao.GreenDaoManager;
import com.example.solamly.solamly.module.rxjava_retrofit.NetManager;

import static com.example.solamly.solamly.module.http_url_client.Retrofit.RetrofitUtil.initRetrofit;

/**
 * @Author SOLAMLY
 * @Date 2018/9/3 17:06
 * @Description: 需要在AndroidManifest中添加
 */

public class BaseApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.setupDatabase(this);
        initRetrofit();
        NetManager.getInstance().initRetrofit();
    }

    public static Context getContext() {
        return mContext;
    }
}

