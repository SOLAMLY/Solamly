package com.example.solamly.basemodule.util.other;

import android.text.TextUtils;

/**
 * @Author: SOLAMLY
 * @Date: 2019/12/3 0003 10:17
 * @Description:
 */
public class Utils {
    public static<T> String getContent(T t){
        if ( null==t || "null".equalsIgnoreCase(t.toString()) || TextUtils.isEmpty(t.toString())){
            return "";
        }else{
            return t.toString();
        }
    }
}
