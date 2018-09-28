package com.example.solamly.solamly.module.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.solamly.solamly.Util.imageloader.BitmapUtil;
import com.example.solamly.solamly.Util.imageloader.LruCacheUtil;
import com.example.solamly.solamly.Util.MeasurementUtil;
import com.example.solamly.solamly.module.http_url_client.HttpUtil;

import java.util.Arrays;

/**
 * @Author SOLAMLY
 * @Date 2018/9/20 14:08
 * @Description: 三个参数：
 * Params:启动任务时输入的参数类型.
 * Progress:后台任务执行中返回进度值的类型.
 * Result:后台任务执行完成后返回结果的类型.
 */

public class ImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    private static final String TAG = "ImageAsyncTask";
    private Context context;
    private String url;

    public ImageAsyncTask(Context context,String url, OnProcedureListener onProcedureListener) {
        this.context = context;
        this.url = url;
        this.onProcedureListener = onProcedureListener;
    }

    /**
     * 执行后台耗时操作前被调用
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "异步加载的准备");
        onProcedureListener.onPreExecute();
    }

    /**
     * 耗时操作在此调用
     */
    @Override
    protected Bitmap doInBackground(String... params) {
        Log.i(TAG, "执行耗时操作");
        return HttpUtil.loadIBitmap(params[0]);
    }

    /**
     * 进度条更新
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.i(TAG, "进度更新" + Arrays.toString(values));
    }

    /**
     * 当doInBackground方法完成后,系统将自动调用此方法,并将doInBackground方法返回的值传入此方法.通过此方法进行UI的更新
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.i(TAG, "更新UI");
        float scale = BitmapUtil.getBitmapScale(bitmap, MeasurementUtil.getScreenWidth(context), 0);
        Bitmap bitmap1 = BitmapUtil.getScaleBitmapForBit(bitmap, scale);
        LruCacheUtil.getInstance().setCache(url,bitmap1);
        onProcedureListener.onPostExecute(bitmap1);
    }

    private OnProcedureListener onProcedureListener;

    interface OnProcedureListener {
        /**
         * 准备阶段
         */
        void onPreExecute();

        /**
         * 耗时操作
         */
        void onDoInBackground();

        /**
         * 进度更新
         * @param progress
         */
        void onProgressUpdate(Integer[] progress);

        /**
         * UI更新
         * @param bitmap
         */
        void onPostExecute(Bitmap bitmap);

    }
}
