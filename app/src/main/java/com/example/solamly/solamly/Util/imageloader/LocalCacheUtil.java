package com.example.solamly.solamly.Util.imageloader;

import android.os.Environment;

import java.io.File;

/**
 * @Author SOLAMLY
 * @Date 2018/9/20 17:24
 * @Description:
 */

public class LocalCacheUtil {
    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/test_solamly/image/cache" ;

    public void setCache(){
        File dir = new File(CACHE_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir,System.currentTimeMillis() + "");
    }


}
