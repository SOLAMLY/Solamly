package com.example.solamly.solamly.module.network.OKHttp;

import android.util.Log;
import android.widget.Toast;

import com.example.solamly.solamly.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author SOLAMLY
 * @Date 2018/9/6 15:43
 * @Description:
 */

public class OKHttp3Util {
    private static final String TAG = "OK_HTTP";

    private static  OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * 异步GET请求
     *
     * @param url
     */
    public static void getAsyncOkHttp(String url) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "Result:" + result);
            }
        });
    }

    /**
     * 同步GET请求
     *
     * @param url
     */
    public static void getOkHttp(final String url) {

        Request request = new Request.Builder().url(url).build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        Log.e(TAG, "Result:" + response.body().string());
                    } else {
                        Log.e(TAG, "error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * POST请求
     *
     * @param url
     */
    public static void postAsynOkHttp(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .add("userid", "7aaa8c8864924421b4cba5a26043bf94")
                .add("pgindex", "1")
                .add("pagesize", "10")
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "url:" + response.request().headers());
                Log.e(TAG, "Result:" + response.body().string());
            }
        });
    }

    private static String fileUploadUrl = "http://120.78.88.242:9080/upload/UploadAuthenticationServlet";
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("image/jpg");           //仅图片上传
    public static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");       //支持大多数文件上传

    /**
     * 支持多文件上传
     *
     * @param fileUrls 文件绝对路径集合
     */
    public static void postAsyncFile(List<String> fileUrls) {
        //定义上传文件类型
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < fileUrls.size(); i++) {
            File file = new File(fileUrls.get(i));
            builder.addFormDataPart("oFileName", file.getName(), RequestBody.create(getMediaType(file.getPath()), file));
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(fileUploadUrl)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "Result" + response.body().string());
                Toast.makeText(BaseApplication.getContext(), "上传成功", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 根据文件路径返回对应的MediaType
     * @param filePath
     * @return
     */
    public static MediaType  getMediaType(String filePath){
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(filePath);
        if (type == null){
            type = "application/octet-stream";
        }
        Log.e(TAG,"MediaType:" + type);
        return MediaType.parse(type);
    }



}
