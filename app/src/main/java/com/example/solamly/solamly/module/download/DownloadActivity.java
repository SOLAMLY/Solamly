package com.example.solamly.solamly.module.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.basemodule.util.other.ToastUtil;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.module.greendao.GreenDaoManager;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.solamly.solamly.module.download.FileBean.DOWN_THREAD_FINISH;
import static com.example.solamly.solamly.module.download.FileBean.UPDATE_PROGRESS;

/**
 * @Author SOLAMLY
 * @Date 2018/10/29 15:42
 * @Description:
 */

public class DownloadActivity extends BaseActivity {
    private static final String TAG = "DownloadActivity";

    @BindView(R.id.seekBar)
    SeekBar mSeekBar;
    @BindView(R.id.btn_start)
    Button mBtnStart;
//    private String path = "http://shouji.360tpcdn.com/181030/e5b87133cc8d179aae7c7b69107cf7a5/com.jingdong.app.mall_62790.apk";
    private String path = "http://111.74.239.245:8666/data/wisegame/e95d3c7a8eec175c/wannengpaizhaoshiwu_5.apk?business_id=9029&task_id=7062169516865421319&from=a1101";

    private static final String STATE_FINISH = "STATE_FINISH";                   //文件下载完成
    private static final String STATE_INTERRUPTED = "STATE_INTERRUPTED";         //下载中断
    private static final String STATE_RUNNING = "STATE_RUNNING";                //正在下载状态
    private static final String STATE_NOT_START = "STATE_NOT_START";            //下载任务未开始
    private String state = STATE_NOT_START;                                     //当前下载状态



    private DownloadThread mDownloadThread;                          //下载线程
    private   FileBean mFileBean;
    private DownloadService mDownloadService;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWN_THREAD_FINISH:
                    Looper.prepare();
                    ToastUtil.ToastShow("停止下载");
                    Looper.loop();
                    break;
                case UPDATE_PROGRESS:
                    mSeekBar.setProgress(msg.arg1);
                    break;
            }
        }
    };


    @Override
    protected int setLayout() {
        return R.layout.activity_download;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        mFileBean = GreenDaoManager.getFileBean(path);
        Log.e(TAG,"数据:" + mFileBean);
            initSeekBar();
    }

    @OnClick({
            R.id.btn_start
    })
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                switch (state) {
                    /**
                     * 如果当前任务未开始状态，转为下载状态
                     */
                    case STATE_NOT_START:
                        startDownLoad(false);
                        break;
                    /**
                     * 如果当前停止下载状态，转为下载状态
                     */
                    case STATE_INTERRUPTED:
                        startDownLoad(false);
                        break;
                    /**
                     * 如果当前是下载状态，转为停止停止状态
                     */
                    case STATE_RUNNING:
                        startDownLoad(true);
                        break;
                    /**
                     * 如果当前是已下载完成状态，进行提示
                     */
                    case STATE_FINISH:
                        ToastUtil.ToastShow("文件已下载");
                        break;
                }
        }
    }

    /**
     * 初始化进度条
     */
    private void initSeekBar() {
        if (mFileBean != null) {
            mSeekBar.setMax(mFileBean.getLength());
            mSeekBar.setProgress(mFileBean.getStartNode());
            mSeekBar.setEnabled(false);
        }
    }

    private void startDownLoad(boolean isPause) {
        if (isPause) {
            mDownloadThread.stopThread();
            state = STATE_INTERRUPTED;
        } else {
            mDownloadThread = new DownloadThread(mHandler, path);
            mDownloadThread.start();
            state = STATE_RUNNING;
        }
    }
}
