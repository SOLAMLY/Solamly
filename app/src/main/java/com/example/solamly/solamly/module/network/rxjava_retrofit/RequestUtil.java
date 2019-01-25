package com.example.solamly.solamly.module.network.rxjava_retrofit;

import com.example.solamly.solamly.module.network.network.Retrofit.CommentBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 15:53
 * @Description:
 */

public class RequestUtil {
    private static  final String TAG = "RequestUtil";
    private static RetrofitApi retrofitApi = NetManager.getRetrofit().create(RetrofitApi.class);


    public static void getCommentLiat(){
        retrofitApi.getCommenList("c1_38b166523382428aa919db4657b7acfa","10","1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<CommentBean>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<List<CommentBean>> listBaseResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
