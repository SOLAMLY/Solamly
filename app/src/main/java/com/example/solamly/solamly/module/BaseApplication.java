package com.example.solamly.solamly.module;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.solamly.solamly.module.greendao.GreenDaoManager;
import com.example.solamly.solamly.module.network.network.Retrofit.RetrofitUtil;
import com.example.solamly.solamly.module.network.rxjava_retrofit.NetManager;
import com.solamly.module_music.retrofit.RetrofitConfig;


/**
 * @Author SOLAMLY
 * @Date 2018/9/3 17:06
 * @Description: 需要在AndroidManifest中添加
 */

public class BaseApplication extends Application {

    private static Context mContext;
    private static final boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        /**
         * 初始化GreenDat
         */
        GreenDaoManager.setupDatabase(this);
        /**
         * 初始化Retrofit
         */
        RetrofitUtil.initRetrofit2();
        RetrofitConfig.initRetrofit();
        NetManager.getInstance().initRetrofit();

        initBaiDuMap();

        initArouter();
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

    /**
     * 初始化ARouter
     */
    private void initArouter() {
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public static Context getContext() {
        return mContext;
    }
}

