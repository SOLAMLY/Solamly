package com.example.solamly.solamly.module.download;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.solamly.solamly.module.greendao.GreenDaoManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.example.solamly.solamly.module.download.FileBean.DOWN_THREAD_FINISH;
import static com.example.solamly.solamly.module.download.FileBean.UPDATE_PROGRESS;

/**
 * @Author SOLAMLY
 * @Date 2018/10/29 16:47
 * @Description: 下载线程: 断点续传逻辑
 */

public class DownloadThread extends Thread {
    private static final String TAG = "DownLoadThread";
    private int startNode = 0;          //开始节点
    private FileBean mFileBean;

    private String mPath;

    private static final String directory = Environment.getExternalStorageDirectory() + "/solamly_test/";      //下载目录
    private static final String fileName = "aaaa.apk";            //文件名

    private int mFileLength;

    private Handler mHandler;

    public DownloadThread(Handler handler, FileBean fileBean) {
        this.mHandler = handler;
        mFileBean = fileBean;
    }

    public DownloadThread(Handler handler, String path) {
        this.mHandler = handler;
        this.mPath = path;
    }

    @Override
    public void run() {
        FileBean mFileBean = GreenDaoManager.getFileBean(mPath);
        /**
         * 根据数据库是否存在该文件的信息进行初始化
         */
        if (mFileBean == null || mFileBean.getLength() == 0) {
            mFileBean = new FileBean();
            mFileLength = getFileLength(mPath);
            startNode = 0;
            Log.e(TAG,"任务未创建，开始初始化");
        } else {
            /**
             * 判断本地文件是否存在，若不存在即时数据库有该文件的信息也需要重新下载
             */
            File mLocalFile = new File(directory + fileName);
            if (!mLocalFile.exists()) {
                mFileLength = getFileLength(mPath);
                startNode = 0;
                Log.e(TAG,"本地文件不存在，重新初始化");
            } else {
                mFileLength = mFileBean.getLength();
                startNode = mFileBean.getStartNode();
                Log.e(TAG,"本地文件存在，可继续下载");
            }
        }

        try {
            URL mURL = new URL(mPath);
            HttpURLConnection mURLConnection = (HttpURLConnection) mURL.openConnection();
            mURLConnection.setConnectTimeout(3000);
            mURLConnection.setRequestMethod("GET");
            mURLConnection.setRequestProperty("Range", "bytes=" + startNode + "-" + mFileLength);

            /**
             * 创建文件夹
             */
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
                Log.e(TAG, "文件夹不存在，创建文件夹成功");
            }
            File mFile = new File(directory + fileName);
            RandomAccessFile mRandomAccessFile = new RandomAccessFile(mFile, "rwd");
//            mRandomAccessFile.setLength(mFileBean.getLength());               // 设置文件的大小
            mRandomAccessFile.seek(startNode);                                   //从上一次下载中断的位置开始写入数据

            long lastTime = 0;              //记录进度条更新时间
            int finishLength = startNode;   //线程中断时的已下载的文件长度

            /**
             * 下载逻辑 状态码：200是普通的下载  206是分段下载
             */
            if (mURLConnection.getResponseCode() == 206) {
                Log.e(TAG,"开始下载");
                InputStream is = mURLConnection.getInputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = is.read(buf)) != -1) {
                    mRandomAccessFile.write(buf, 0, len);
                    /**
                     * 实时存储进度信息
                     */
                    finishLength += len;
                    mFileBean.setUrl(mPath);
                    mFileBean.setStartNode(finishLength);
                    mFileBean.setLength(mFileLength);
                    GreenDaoManager.saveFileSchedule(mFileBean);

                    Log.e(TAG, "下载长度：" + finishLength + " / " + mFileLength);
                    long currentTime = System.currentTimeMillis();
                    /**
                     *  每500毫秒更新一次进度条
                     */
                    if (currentTime - lastTime > 500) {
                        Message mMessage = new Message();
                        mMessage.what = UPDATE_PROGRESS;
                        mMessage.arg1 = finishLength;
                        mHandler.handleMessage(mMessage);
                        lastTime = currentTime;
                    }
                    /**
                     * 任务中断下载逻辑
                     */
                    if (isPause) {
                        Log.e(TAG, "中断线程");
                        break;
                    }
                }
                is.close();
            }
            mRandomAccessFile.close();

            /**
             * 任务下载完成逻辑
             */
            Message msg = new Message();
            msg.what = DOWN_THREAD_FINISH;
            mHandler.handleMessage(msg);
            if (mFileLength == finishLength) {
                Log.e(TAG, "下载完成");
            }
        } catch (IOException mE) {
            mE.printStackTrace();
        }
    }

    private boolean isPause = false;

    /**
     * 中断线程
     */

    public void stopThread() {
        this.isPause = true;
    }


    /**
     * 获取下载文件的长度  - 同步方式
     *
     * @param path 文件URL
     * @return
     */
    private int getFileLength(String path) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url(path).build();
        try {
            okhttp3.Response mResponse = mOkHttpClient.newCall(mRequest).execute();
            if (mResponse != null && mResponse.isSuccessful()) {
                long contentLength = mResponse.body().contentLength();
                mResponse.body().close();
                return (int) contentLength;
            }
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        return 0;
    }
}
