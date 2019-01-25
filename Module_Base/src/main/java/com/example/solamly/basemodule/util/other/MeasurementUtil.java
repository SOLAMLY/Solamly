package com.example.solamly.basemodule.util.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * @Author SOLAMLY
 * @Date 2018/7/20 14:41
 * @Description:
 */

public class MeasurementUtil {
    private static final String TAG = "测量";


    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 测量控件的宽高
     * `注意:控件绘制完成后才能获取真实的宽高
     *
     * @param view
     * @return
     */
    public static int[] getViewWidthHeight(final View view) {
        int viewProperty[] = new int[2];
        viewProperty[0] = view.getWidth();
        viewProperty[1] = view.getHeight();
        Log.e("Width/Height", "width:" + viewProperty[0] + "/ height:" + viewProperty[1]);
        return viewProperty;

    }

    /**
     * 获取图片原始尺寸
     *
     * @param bitmap
     * @return
     */
    public static int[] getImageOriginalSize(Bitmap bitmap) {
        int[] wh = new int[2];
        wh[0] = bitmap.getWidth();
        wh[1] = bitmap.getHeight();
        Log.e(TAG, "图片原始尺寸:" + wh[0] + " / " + wh[1]);
        return wh;
    }


    /**
     * 得到图片宽高比
     *
     * @param bitmap
     * @return
     */
    public static float getImageScale(Bitmap bitmap) {
        int[] originSize = getImageOriginalSize(bitmap);
        float scale = (float) originSize[0] / (float) originSize[1];
        Log.e(TAG, "图片宽高比例: " + scale);
        return scale;
    }

    /**
     * 按原始图片比例进行缩放后的图片尺寸
     *
     * @param bitmap
     * @return
     */
    public static int[] getImageZoomSize(Bitmap bitmap, int width) {
        int[] wh = new int[2];
        wh[0] = width;
        wh[1] = (int) ((float) width / getImageScale(bitmap));
        Log.e(TAG, "图片等比例缩放后:"  + wh[0] + " / " + wh[1]);
        return wh;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
