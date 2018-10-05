package com.example.solamly.solamly;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
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

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    public static Context getContext() {
        return mContext;
    }
}

