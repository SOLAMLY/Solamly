package com.example.solamly.solamly.Util.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

/**
 * @Author SOLAMLY
 * @Date 2018/9/19 11:24
 * @Description:
 */

public class BitmapUtil {
    private static final String TAG = "BitmapUtil";
    /**
     * 根据控件实际显示的宽高获得原始Bitmap的要缩放的比例
     *
     * @param bitmap 原始Bitmap
     * @param width 控件的实际显示宽
     * @param height 控件的实际显示宽
     * @return
     */
    public static float getBitmapScale(Bitmap bitmap, int width, int height) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getWidth();
        float scale = 1.0f;
        if (bitmapWidth > width || bitmapHeight > height) {
            float widthScale = (float) width / (float) bitmapWidth;
            float heightScale = (float) height / (float) bitmapHeight;
            //选取数值较大的比例
            scale = widthScale < heightScale ? heightScale : widthScale;
        }
        Log.e("压缩比", scale + "");
        return scale;
    }

    /**
     * Bitmap 压缩   ---  缩放法进行压缩
     *
     * @param bitmap 要压缩的Bitmap
     * @param scale 缩放比
     * @return 压缩后的Bitmap
     */
    public static Bitmap getScaleBitmapForBit(Bitmap bitmap,float scale) {

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        Bitmap bm;
        bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Log.e(TAG, "压缩前:宽/高" + bitmap.getWidth() + "/" + bitmap.getHeight() + ",大小" + bitmap.getByteCount() / 1024 + "KB");
        Log.e(TAG, "压缩后:宽/高" + bm.getWidth() + "/" + bm.getHeight() + ",大小" + bm.getByteCount() / 1024 + "KB");
        return bm;
   }

    /**
     * 获取缩放后的本地图片
     *
     * @param filePath 文件路径
     * @param width    宽
     * @param height   高
     * @return
     */
    public static Bitmap getBitmapFroUrl(String filePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;

        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(filePath, options);
    }

}
