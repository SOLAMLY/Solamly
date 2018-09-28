package com.example.solamly.solamly.module.dagger.module;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @Author SOLAMLY
 * @Date 2018/9/12 16:05
 * @Description:
 */

@Module
public class HttpModule {
    @Provides
    OkHttpClient proviceOkHttpClient(){
        return new OkHttpClient();
    }
}
