package com.example.solamly.solamly.module.network.rxjava_retrofit;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 16:30
 * @Description:
 */

public class ResponseTransformer {

    public static class ResponseFunction<T> implements Function<BaseResponse<T>,ObservableSource<T>>{

        @Override
        public ObservableSource<T> apply(@NonNull BaseResponse<T> tResponse) throws Exception {
            return null;
        }
    }
}
