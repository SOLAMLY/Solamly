package com.example.solamly.solamly.module.mv;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.solamly.basemodule.base.ui.BaseTestFragment;

/**
 * @Author: SOLAMLY
 * @Date: 2019/9/19 0019 16:17
 * @Description:
 */
public class MyViewModelFragment1 extends BaseTestFragment {
    MyViewModel mMyViewModel;
    public Context mContext;private String mString = "哈哈哈哈";
    @Override
    protected void initView() {
        super.initView();
        mContext = this.getContext();

        mMyViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        mBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyViewModel.setLiveData(mString);
            }
        });
        mMyViewModel.getLiveData().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("TAG", s);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mString = "圣诞快乐房间乱收费";
            }
        }).start();
    }
}
