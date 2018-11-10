package com.example.solamly.solamly.module.network.OKHttp;

import android.os.Handler;

import com.google.android.gms.common.api.ResultCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * @Author SOLAMLY
 * @Date 2018/9/6 17:43
 * @Description: OK HTTP 封装
 */

public class OkHttpManager {
    private static OkHttpManager instance;
    private OkHttpClient okHttpClient;
    private Handler mHandler;

    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

    public OkHttpManager() {
        okHttpClient = new OkHttpClient();
    }

    public Request getAsyncOkHttp2(String url, HashMap<String, String> map, ResultCallback callback) {
        try {
            MultipartBody.Builder bulider = new MultipartBody.Builder();
            for (Map.Entry entry : map.entrySet()) {
                String key = URLEncoder.encode(entry.getKey().toString(), "utf-8");
                String value = URLEncoder.encode(entry.getValue().toString(), "utf-8");
                bulider.addFormDataPart(key, value);
            }
            RequestBody requestBody = bulider.build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            return request;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }




}
