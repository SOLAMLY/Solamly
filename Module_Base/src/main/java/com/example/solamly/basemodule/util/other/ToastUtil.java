package com.example.solamly.basemodule.util.other;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.solamly.basemodule.BaseModelApplication;

/**
 * @Author SOLAMLY
 * @Date 2018/9/20 14:38
 * @Description:
 */

public class ToastUtil {

    private static Toast toast;

    public static void toast(String s) {
        if (!TextUtils.isEmpty(s)) {
            if (toast == null) {
                toast = Toast.makeText(BaseModelApplication.getContext(), s, Toast.LENGTH_SHORT);
            } else {
                toast.setText(s);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    public static void toastShow(String s) {
        Toast.makeText(BaseModelApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
