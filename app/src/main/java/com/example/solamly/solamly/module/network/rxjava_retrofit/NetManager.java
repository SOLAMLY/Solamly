package com.example.solamly.solamly.module.network.rxjava_retrofit;

import com.example.solamly.solamly.module.network.network.OKHttp.LoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 15:22
 * @Description:
 */

public class NetManager {
    private static NetManager instance;
    private static Retrofit retrofit;
    private static final String BaseUrl = "http://fz.meibbc.com/";

    /**
     * 这里是一个千篇一律的套路，如果封装全局使用的框架，并且需要和整个软件有一样长的生命周期。那就有几个特点：
     * 在Application里面初始化。
     * 使用单例模式。
     * 在一个"管理类"中初始化所需要的所有参数。
     *
     * @return
     */
    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    public  void initRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
