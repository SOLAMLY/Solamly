package com.example.solamly.solamly.module.design_mode.factory;

import android.util.Log;

/**
 * @Author SOLAMLY
 * @Date 2018/9/27 9:36
 * @Description:
 */

public class Peach implements Fruits {
    private static final String TAG = "Peach";
    @Override
    public void getPrice() {
        Log.e(TAG,"this is Peach");
    }
}
