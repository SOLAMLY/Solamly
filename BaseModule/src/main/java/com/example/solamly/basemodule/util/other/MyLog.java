package com.example.solamly.basemodule.util.other;

import android.util.Log;

/**
 * @Author SOLAMLY
 * @Date 2018/9/5 14:17
 * @Description:
 */

public class MyLog  {
    /**
     * 输出打印控制
     */
    private static boolean IS_LOG = true;

    public static void i(String TAG,String msg){
        if (IS_LOG){
            Log.i(TAG,msg);
        }
    }

    public static void e(String TAG,String msg){
        if (IS_LOG){
            Log.e(TAG,msg);
        }
    }

}
