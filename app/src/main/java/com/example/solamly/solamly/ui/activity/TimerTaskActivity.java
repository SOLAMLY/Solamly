package com.example.solamly.solamly.ui.activity;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.solamly.basemodule.base.ui.NewBaseActivity;
import com.example.solamly.basemodule.util.other.ToastUtil;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.databinding.ActivityTimerTaskBinding;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


/**
 * @Author: SOLAMLY
 * @Date: 2019/12/3 0003 09:49
 * @Description: 各种定时器方法实现
 */
public class TimerTaskActivity extends NewBaseActivity<ActivityTimerTaskBinding> {
    public static final int ONECE_TIME = 1000;
    public static final int WHAT1 = 1;

    private int count = 10;
    private int Mode = 1;

    private LooperHandler mHandler = new LooperHandler(this);

    /**
     * handler 持有当前Activity的弱引用防止内存泄露
     * <p>
     * ①先说handler导致activity内存泄露的原因：
     * handler发送的消息在当前handler的消息队列中，如果此时activity finish掉了，那么消息队列的消息依旧会由handler进行处理，
     * 若此时handler声明为内部类（非静态内部类），我们知道内部类天然持有外部类的实例引用，
     * 这样在GC垃圾回收机制进行回收时发现这个Activity居然还有其他引用存在，因而就不会去回收这个Activity，进而导致activity泄露。
     * <p>
     * ②为何handler要定义为static？
     * 因为静态内部类不持有外部类的引用，所以使用静态的handler不会导致activity的泄露
     * <p>
     * ③为何handler要定义为static的同时，还要用WeakReference 包裹外部类的对象？
     * 这是因为我们需要使用外部类的成员，可以通过"activity. "获取变量方法等，如果直接使用强引用，显然会导致activity泄露。
     */
    private static class LooperHandler extends Handler {
        WeakReference<TimerTaskActivity> mWeakReference;

        LooperHandler(TimerTaskActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TimerTaskActivity loopersActivity = mWeakReference.get();
            switch (msg.what) {
                case WHAT1:
                    loopersActivity.count--;
                    loopersActivity.mBinding.tvTimer.setText("倒计时：" + loopersActivity.count);
                    switch (loopersActivity.Mode) {
                        case R.id.btn_handler_postDelayed:
                            loopersActivity.handlerPostDelayed();
                            break;
                    }
            }
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_timer_task;
    }

    @Override
    public String setTitle() {
        return "定时器";
    }

    @Override
    public void init() {
        mBinding.setOnClick(this);
        mBinding.tvTimer.setText("倒计时：开始");

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Mode = v.getId();
        switch (v.getId()) {
            case R.id.btn_rester:
                mBinding.tvTimer.setText("倒计时：开始");
                count = 10;
                dispose();
                break;
            case R.id.btn_handler_postDelayed:
                handlerPostDelayed();
                break;
            case R.id.btn_handle_timer:
                timeTask();
                break;
            case R.id.btn_timerTask:
                timeTask2();
                break;
            case R.id.btn_scheduledExecutorService:
                scheduledExecutorService();
                break;
            case R.id.btn_rxJavaTimer:
                rxJavaTimer();
                break;
            case R.id.btn_rxJavaInterval:
                rxJavaInterval();
                break;
            case R.id.btn_countDownTimer:
                CountDownTimer();
                break;
        }
    }


    /**
     * Handler + PostDelayed 方式
     */
    private void handlerPostDelayed() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage(WHAT1);
                mHandler.sendMessage(msg);
            }
        }, ONECE_TIME);
    }


    /**
     * Handler + Timer + TimerTask 方式
     * 注意:实际使用时，在不用时或者finish()时记得把TimerTask和Timer都销毁掉 :
     * mTimerTask.cancel();  mTimerTask = null;
     * mTimer.cancel();  mTimer = null;
     */
    private void timeTask() {
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = mHandler.obtainMessage(WHAT1);
                mHandler.sendMessage(message);
            }
        }, ONECE_TIME); //第二个参数：首次执行的延迟时间;只执行一次TimerTask
    }

    /**
     * Timer + TimerTask
     * 注意:实际使用时，在不用时或者finish()时记得把TimerTask和Timer都销毁掉 :
     * mTimerTask.cancel();  mTimerTask = null;
     * mTimer.cancel();  mTimer = null;
     */
    private void timeTask2() {
        final Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                count--;
                mBinding.tvTimer.setText("倒计时：" + count);
                if (count <= 0) mTimer.cancel();
            }
        }, ONECE_TIME, ONECE_TIME);      //第二个参数：首次执行的延迟时间；第三个参数：间隔ONECE_TIME秒后重复执行TimerTask
    }

    /**
     * ScheduledExecutorService + Handler 方式
     */
    private ScheduledExecutorService scheduled;

    private void scheduledExecutorService() {
        scheduled = new ScheduledThreadPoolExecutor(1);

        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Message message = mHandler.obtainMessage(WHAT1);
                mHandler.sendMessage(message);
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }


    private static Disposable mDisposable;
    /**
     * RxJava - timer方式：执行一次
     * 创建一个Observable，并延迟发送一次的操作符,但不会周期运行
     */
    private void rxJavaTimer() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mBinding.tvTimer.setText("倒计时：" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dispose();
                    }

                    @Override
                    public void onComplete() {
                        dispose();

                    }
                });
    }

    /**
     * RxJava - interval方式：周期运行
     * 创建一个Observable，并延迟发送一次的操作符,会周期运行,所以需要在适当时机取消订阅 dispose();
     */
    private void rxJavaInterval() {
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(10)               //设置总共发送多少次
                .map(new Function<Long, Long>() {   // long值是从小到大，倒计时需要将值倒置
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return 9 - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mBinding.tvTimer.setText("倒计时：" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消订阅
     */
    public static void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    /**
     * CountDownTimer 方式
     * 暂停倒计时： mCountDownTimer.cancel();   mCountDownTimer = null;
     */
    private void CountDownTimer(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                mBinding.tvTimer.setText("倒计时: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mBinding.tvTimer.setText("done!");
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
