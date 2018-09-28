package com.example.solamly.solamly.Util;

import android.widget.Toast;

import com.example.solamly.solamly.BaseApplication;

/**
 * @Author SOLAMLY
 * @Date 2018/9/20 14:38
 * @Description:
 */

public class ToastUtil {
    public static void ToastShow(String content){
        Toast.makeText(BaseApplication.getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
