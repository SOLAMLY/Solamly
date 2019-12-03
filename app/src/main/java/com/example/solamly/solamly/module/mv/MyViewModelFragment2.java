package com.example.solamly.solamly.module.mv;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.solamly.basemodule.base.ui.BaseTestFragment;

/**
 * @Author: SOLAMLY
 * @Date: 2019/9/19 0019 16:17
 * @Description:
 */
public class MyViewModelFragment2 extends BaseTestFragment {
    MyViewModel mMyViewModel;
    @Override
    protected void initView() {
        super.initView();
        mMyViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        mMyViewModel.getLiveData().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("TAG", s);
            }
        });
    }
}
