package com.example.solamly.solamly.ui.activity;

import android.content.Context;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.solamly.basemodule.util.other.MeasurementUtil;
import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;

import butterknife.BindView;

/**
 * @Author SOLAMLY
 * @Date 2018/11/21 17:53
 * @Description:
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.tv_100dp)
    TextView mTv100dp;

    private Context mContext;
    @Override
    protected int setLayout() {
        return R.layout.activity_custom_layout;
    }

    @Override
    protected void initView() {
        mContext = this;

        Log.i("直接获取的宽高" , mTv100dp.getBottom() + ":" + mTv100dp.getTop());
        mTv100dp.post(new Runnable() {
            @Override
            public void run() {
                Log.i("放到post里的宽高" , mTv100dp.getBottom() + " :" + mTv100dp.getTop());
                Log.i("放到post里的宽高 - 转换后的" , MeasurementUtil.px2dip(mContext,mTv100dp.getBottom()) + " :" +MeasurementUtil.dip2px(mContext,mTv100dp.getTop()));
            }
        });

        mTv100dp.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTv100dp.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.i("使用ViewTree获取的宽高" , mTv100dp.getBottom() + " :" + mTv100dp.getTop());
                Log.i("使用ViewTree获取的宽高 - 转换后的" , MeasurementUtil.px2dip(mContext,mTv100dp.getBottom()) + " :" +MeasurementUtil.dip2px(mContext,mTv100dp.getTop()));

            }
        });
    }

    @Override
    protected void initData() {

    }



}
