package com.example.solamly.solamly.module.dagger.test;

import android.util.Log;

import static com.example.solamly.basemodule.base.ui.BaseConstant.TAG_DAGGER;

/**
 * @Author SOLAMLY
 * @Date 2018/9/12 15:31
 * @Description:
 */

public class SecondTest {


    private String msg;

    public SecondTest(String msg) {
        this.msg = msg;
    }

    public void log(){
        Log.i(TAG_DAGGER,"SecondTest" + msg);
    }
}
