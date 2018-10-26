package com.example.solamly.basemodule.util.imageloader;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * @Author SOLAMLY
 * @Date 2018/9/20 15:51
 * @Description: 图片缓存
 */

public class LruCacheUtil {
    private static final String TAG = "LruCacheUtil";
    private static LruCacheUtil mInstance;
    private static LruCache<String, Bitmap> lruCache;

    public static LruCacheUtil getInstance() {
        if (mInstance == null) {
            synchronized (LruCacheUtil.class) {
                if (mInstance == null) {
                    mInstance = new LruCacheUtil();
                }
            }
        }
        return mInstance;
    }

    public LruCacheUtil() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    public void setCache(String url, Bitmap bitmap) {
        Log.e(TAG, "添加内存缓存:" + url);
        lruCache.put(url, bitmap);
    }

    public Bitmap getCache(String url) {
        Bitmap bitmap = lruCache.get(url);
        if (bitmap == null) {
            Log.e(TAG, "读取内存缓存失败:" + bitmap);
        } else {
            Log.e(TAG, "读取内存缓存:" + bitmap.getByteCount() / 1024 + "KB");
        }
        return bitmap;
    }


}
