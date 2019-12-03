package com.example.solamly.solamly.module;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.solamly.basemodule.BaseModelApplication;
import com.example.solamly.solamly.module.greendao.GreenDaoManager;
import com.example.solamly.solamly.module.network.network.Retrofit.RetrofitUtil;
import com.example.solamly.solamly.module.network.rxjava_retrofit.NetManager;



/**
 * @Author SOLAMLY
 * @Date 2018/9/3 17:06
 * @Description: 需要在AndroidManifest中添加
 */

public class BaseApplication extends BaseModelApplication {

    private static final boolean isDebug = true;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化GreenDat
         */
        GreenDaoManager.setupDatabase(this);
        /**
         * 初始化Retrofit
         */
        RetrofitUtil.initRetrofit2();
        NetManager.getInstance().initRetrofit();

        initArouter();
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

}

