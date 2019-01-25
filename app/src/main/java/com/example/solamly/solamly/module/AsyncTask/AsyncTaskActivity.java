package com.example.solamly.solamly.module.AsyncTask;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.basemodule.util.imageloader.LruCacheUtil;
import com.example.solamly.solamly.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author SOLAMLY
 * @Date 2018/9/6 15:31
 * @Description:
 */

public class AsyncTaskActivity extends BaseActivity implements ImageAsyncTask.OnProcedureListener {
    private static final String TAG = "AsyncTask";
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.imageView)
    ImageView imageView;
    private ImageAsyncTask imageAsyncTask ;

    private String[] urls = new String[]{
            "http://p2.so.qhimgs1.com/t018ea04c447dacecc2.jpg",
            "http://p2.so.qhimgs1.com/t018ea04c447dacecc2.jpg",
            "http://p3.so.qhmsg.com/t01f3320b3b07e108f8.jpg",
            "http://p3.so.qhimgs1.com/t013668dd3541f836d3.jpg",
            "http://p1.so.qhmsg.com/t01c00e7c5c08fd3a96.jpg",
            "http://p3.so.qhimgs1.com/t019736877f573c3e53.jpg",
            "http://p4.so.qhmsg.com/t0187a49ac8d530abf9.jpg",
            "http://p5.so.qhimgs1.com/t013e87bfa62b52339b.jpg"
    };

    int index;
    @Override
    protected int setLayout() {
        return R.layout.activiyt_asynctask;
    }

    @Override
    protected void initView() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
    }



    @OnClick({
            R.id.btn_start
    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        index++;
        index = index % 8;
        String url = urls[index];
        Bitmap bitmap = LruCacheUtil.getInstance().getCache(url);
        if (bitmap == null){
            Log.e(TAG,"从网络下载图片");
            imageAsyncTask = new ImageAsyncTask(this,url,this);
            imageAsyncTask.execute(url);
        }else{
            Log.e(TAG,"从内存缓存下载图片");
            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPreExecute() {
        imageView.setImageBitmap(null);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressUpdate(Integer[] progress) {

    }

    @Override
    public void onDoInBackground() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageAsyncTask.cancel(true);
    }
}
