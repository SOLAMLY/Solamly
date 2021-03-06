package com.example.solamly.solamly.module.network.network.OKHttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author SOLAMLY
 * @Date 2018/9/7 17:46
 * @Description:
 */

public class LoggerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Log.e("TAG",String.format("发送请求 %s",request.url()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.e("TAG",String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
