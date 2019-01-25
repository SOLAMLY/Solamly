package com.example.solamly.solamly.module.dagger.test;

import android.util.Log;

import javax.inject.Inject;

import static com.example.solamly.basemodule.base.ui.BaseConstant.TAG_DAGGER;

/**
 * @Author SOLAMLY
 * @Date 2018/9/12 15:06
 * @Description:
 */

public class FirstTest {

    @Inject
    public FirstTest() {
    }

    public void log(){
        Log.i(TAG_DAGGER,"FirstTest");
    }

}
