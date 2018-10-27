package com.example.solamly.basemodule.util.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * @Author SOLAMLY
 * @Date 2018/8/31 10:35
 * @Description:
 */

public class BitmapDrawableUtil {
    /**
     * Drawable -> Bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 根据路径得到Bitmap
     *
     * @param path
     * @return
     */
    public static Bitmap getImageBitmap(String path) {
        return BitmapFactory.decodeFile(path);
    }

    public static Bitmap drawableToBitmap(Context context, int drawable){
        Resources resource = context.getResources();
        return BitmapFactory.decodeResource(resource,drawable);
    }
}
