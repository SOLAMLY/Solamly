package com.example.solamly.basemodule.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.solamly.basemodule.util.other.MeasurementUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author SOLAMLY
 * @Date 2018/10/26 11:45
 * @Description: 适用于DataBinding
 */

public abstract class BaseDataBindingActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();
    protected ViewDataBinding mViewDataBinding;
    Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"--------- onCreate");
        initStatusBar();
        mViewDataBinding = DataBindingUtil.setContentView(this, setContentLayout());
        //注册EventBus
//        EventBus.getDefault().register(this);
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();
        setListeners();
    }

    protected abstract int setContentLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListeners();

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "--------- onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "--------- onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "--------- onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "--------- onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "--------- onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "--------- onDestroy");
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    /**
     * 初始化标题栏
     * 填充标题栏高度的空间
     */
    public void initTitleBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = MeasurementUtil.getStatusBarHeight(this);
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    /**
     * 填充状态栏高度
     * * @param view
     */
    public void initStatusBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = MeasurementUtil.getStatusBarHeight(this);
            view.getLayoutParams().height = statusBarHeight;

        }
    }


    /**
     * 设置沉浸式状态栏
     * 透明状态栏后还需解决标题栏跟布局重叠的问题
     * 方案一 ： 配合在布局文件中使用:
     * android:clipToPadding="true"
     * android:fitsSystemWindows="true"
     * 方案二： 调用{@link #initTitleBar(View)}
     */
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    /**
     * 跳转事件
     *
     * @param cla
     * @param bundle
     */
    protected void skip(Class<? extends Activity> cla, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cla);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 路由跳转
     * @param path
     * @param bundle
     */
    protected void skipARouter(String path,@Nullable Bundle bundle){
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    /**
     * 冒泡 - 短
     *
     * @param msg
     */
    protected void showToastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * EventBus接收器
     *
     * @param action
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onEvent(String action) {

    }
}
