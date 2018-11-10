//package com.example.solamly.solamly.module.HttpURLClient;
//
//import android.util.Log;
//
//import com.squareup.okhttp.Call;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.FormEncodingBuilder;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
//
//import java.io.IOException;
//
///**
// * @Author SOLAMLY
// * @Date 2018/9/6 15:43
// * @Description:
// */
//
//public class OKHttp2Util {
//    private static final String TAG = "OK_HTTP";
//
//    /**
//     * 异步GET请求
//     *
//     * @param url
//     */
//    public static void getAsyncOkHttp(String url) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url(url)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.e(TAG, "error");
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String result = response.body().string();
//                Log.e(TAG, "Result:" + result);
//
//            }
//        });
//    }
//
//    /**
//     * 同步GET请求
//     *
//     * @param url
//     */
//    public static void getOkHttp(final String url) {
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().url(url).build();
//        final Call call = okHttpClient.newCall(request);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response response = call.execute();
//                    if (response.isSuccessful()) {
//                        Log.e(TAG, "Result:" + response.body().string());
//                    } else {
//                        Log.e(TAG, "error");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    public static void postAsynOkHttp(String url) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new FormEncodingBuilder()
//                .add("userid", "7aaa8c8864924421b4cba5a26043bf94")
//                .add("pgindex", "1")
//                .add("pagesize", "10")
//                .build();
//
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.e(TAG, "error");
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                Log.e(TAG,"url:" +response.request().urlString());
//                Log.e(TAG,"Result:"+ response.body().string());
//            }
//
//        });
//
//
//    }
//
//
//}
