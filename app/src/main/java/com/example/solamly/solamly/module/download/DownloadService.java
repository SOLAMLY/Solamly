package com.example.solamly.solamly.module.download;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.solamly.solamly.module.greendao.GreenDaoManager;

/**
 * @Author SOLAMLY
 * @Date 2018/11/2 15:26
 * @Description:
 */

public class DownloadService extends Service{

    private Handler mHandler;
    private String path;
    private DownloadThread mDownloadThread;
    public DownloadService(Handler mHandler,String path ) {
    this.mHandler = mHandler;
        this.path = path;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //下载
        FileBean mFileBean = GreenDaoManager.getFileBean(path);
        mDownloadThread = new DownloadThread(mHandler,mFileBean);
        mDownloadThread.start();
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mDownloadThread.stopThread();
    }
}
