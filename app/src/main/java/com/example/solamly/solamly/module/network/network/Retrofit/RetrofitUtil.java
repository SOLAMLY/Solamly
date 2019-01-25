package com.example.solamly.solamly.module.network.network.Retrofit;

import android.util.Log;
import android.widget.Toast;

import com.example.solamly.solamly.module.BaseApplication;
import com.example.solamly.solamly.module.network.network.OKHttp.LoggerInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author SOLAMLY
 * @Date 2018/9/8 13:09
 * @Description:
 */

public class RetrofitUtil {
    private static final String TAG = "Retrofit";
    private static Retrofit retrofit;
    private static Retrofit retrofitUpload;
    private static final String BaseUrl = "http://fz.meibbc.com/";
    private static final String BaseUrlUpload = "http://120.78.88.242:9080/";

    public static void initRetrofit2() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitUpload = new Retrofit.Builder()
                .baseUrl(BaseUrlUpload)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 网络请求和回调
     *
     * @param call
     */
    public static void listener(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG, "请求结果返回:" + response.body().string());
                    Toast.makeText(BaseApplication.getContext(), "请求结果返回" +  response.body(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }


        });
    }


    /**
     * 返回 MultipartBody.Part 集合
     *
     * @param paths 文件路径集合
     * @param key   后台接收的参数名
     * @return
     */
    public static List<MultipartBody.Part> getMultipartBodyList(List<String> paths, String key) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (String path : paths) {
            File file = new File(path);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), body);
            parts.add(part);
        }
        return parts;
    }

    /**
     * 返回 RequestBody
     *
     * @param value
     * @return
     */
    public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }


    /**
     * 普通带参POST请求
     */
    public static void postRetrofitM() {
        Api api = retrofit.create(Api.class);
        Call<CommentBean> call = api.getCommenList("c1_38b166523382428aa919db4657b7acfa", "10", "1");
        call.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                Log.e(TAG, "请求结果返回:" + response.body());
                Toast.makeText(BaseApplication.getContext(), "请求结果返回:" +  response.body(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable throwable) {
                Log.e(TAG, "请求出错");
            }
        });
    }

    /**
     * 上传单个文件
     *
     * @param url 文件路径
     */
    public static void postRetrofitUpload(String url) {
        Api api = retrofitUpload.create(Api.class);
        File file = new File(url);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        RequestBody requestBody1 = getRequestBody("userid");
        listener(api.uploadImage(part, requestBody1));
    }

    /**
     * 上传文件集合
     */
    public static void postRetrofitUploadLlist(List<String> paths, String key) {
        Api api = retrofitUpload.create(Api.class);
        List<MultipartBody.Part> parts = getMultipartBodyList(paths, key);
        listener(api.uploadMoreImage(parts));
    }

}
