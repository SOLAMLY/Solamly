package com.solamly.module_music.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.solamly.module_music.retrofit.BaseUrl.BASE_URL;
import static com.solamly.module_music.retrofit.BaseUrl.BASE_URL_UPLOAD;


/**
 * @Author SOLAMLY
 * @Date 2018/10/26 17:35
 * @Description:
 */

public class RetrofitConfig {
    private static Retrofit mRetrofit;
    private static Retrofit mRetrofitUpload;

    public static void initRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitUpload = new Retrofit.Builder()
                .baseUrl(BASE_URL_UPLOAD)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getmRetrofit() {
        return mRetrofit;
    }
    public static Retrofit getmRetrofitUpload(){
        return mRetrofitUpload;
    }
}
