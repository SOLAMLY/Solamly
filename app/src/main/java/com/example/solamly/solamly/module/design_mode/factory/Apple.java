package com.example.solamly.solamly.module.design_mode.factory;

import android.util.Log;

/**
 * @Author SOLAMLY
 * @Date 2018/9/27 9:35
 * @Description:
 */

public class Apple implements Fruits {
    private static final String TAG = "Apple";
    @Override
    public void getPrice() {
        Log.e(TAG,"this is Apple");
    }
}
