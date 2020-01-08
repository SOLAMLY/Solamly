package com.example.solamly.basemodule;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.solamly.basemodule.constant.Constant;
import com.example.solamly.basemodule.helpaer.CustomLogCatStrategy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.smtt.sdk.QbSdk;


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
        initBaiDuMap();
        //初始化X5内核
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }
            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("@@","加载内核是否成功:"+b);
            }
        });

        //Logger初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodOffset(7)
                .logStrategy(new CustomLogCatStrategy())
                .tag(Constant.TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
//                return super.isLoggable(priority, tag); //都打印
                return BuildConfig.DEBUG;           //Debug模式才打印信息
            }

        });
    }

    /**
     * 初始化百度地图
     */
    private void initBaiDuMap() {
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
