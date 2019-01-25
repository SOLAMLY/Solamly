package com.example.solamly.solamly.module.network.rxjava;

import android.database.Observable;

import com.example.solamly.solamly.module.network.network.Retrofit.CommentBean;
import com.example.solamly.solamly.module.network.network.Retrofit.TestBean;

import retrofit2.http.GET;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 11:38
 * @Description:
 */

public interface RxJavaApi {
        @GET
        Observable<TestBean> login(String s);

        @GET
        Observable<CommentBean> register(String s,String s1);

}
