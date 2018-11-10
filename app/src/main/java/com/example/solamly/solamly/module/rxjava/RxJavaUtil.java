package com.example.solamly.solamly.module.rxjava;

import android.util.Log;

import com.example.solamly.solamly.module.network.Retrofit.Api;
import com.example.solamly.solamly.module.network.Retrofit.CommentBean;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.solamly.solamly.module.network.Retrofit.RetrofitUtil.getRetrofit;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 15:48
 * @Description:
 */

public class RxJavaUtil {
    public static final String TAG = "RxJava";
    private static Observable<Integer> observable;
    private  static Observable<String> observableB;

    private static Flowable<String> flowable ;
    CompositeDisposable c = new CompositeDisposable();      //Disposable 容器  通过add添加Disposable，clear()清除所有Disposable

    /**
     * 发送器   --  上游
     * <p>
     * 发送事件需要满足以下规则
     * 1. 上游可以发送无限个onNext, 下游也可以接收无限个onNext.
     * 2. 当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
     * 3. 当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
     * 4. 上游可以不发送onComplete或onError.
     * 5. 最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
     * ( 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.)
     */
    public static void send() {
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                for (int i = 0; i < 3; i++) {
                    Log.e(TAG,"send" + i);
                    observableEmitter.onNext(i);
                }

                observableEmitter.onNext(5555);

            }
        })
                .subscribeOn(Schedulers.io())
        ;

        observableB = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                for (int i = 0; ; i++) {
                    Log.e(TAG, "send0"+ i);
                    observableEmitter.onNext("" + i);
                }
            }
        });

        flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                for (int i = 0; i<500 ; i++) {
                    Log.e(TAG,"send i = " + i);
                    flowableEmitter.onNext("i = " + i);
                }
            }
        }, BackpressureStrategy.ERROR) ;//这个参数是用来选择背压
    }

    /**
     * 最基础 接收器   --  下游
     * <p>
     * public final Disposable subscribe() {}       表示下游不关心任何事件,你上游尽管发你的数据去吧, 老子可不管你发什么.
     * public final Disposable subscribe(Consumer<? super T> onNext) {}     表示下游只关心onNext事件, 其他的事件我假装没看见
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {}  同理
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {}     同理
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {}       同理
     * public final void subscribe(Observer<? super T> observer) {} 下游关心任何事件
     */
    private static void accept() {

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

                .subscribe(new Observer<Integer>() {

                    private Disposable dis;

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        Log.e(TAG, "onSubscribe");
                        dis = disposable;
                    }

                    @Override
                    public void onNext(Integer s) {
                        Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                        Log.e(TAG, "onNext");
                        Log.e(TAG, "接收" + s);
                        if (s.equals("BBB")) {
                            //阻断接收
                            dis.dispose();
                            Log.e(TAG, "isDispose");
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });


    }

    /**
     * map转换符
     * 通过Map, 可以将上游发来的事件转换为任意的类型, 可以是一个Object, 也可以是一个集合
     */
    private static void map() {
        observable
                //将Integer 类型转换为 String 类型
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer i) throws Exception {
                        return "map" + i;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, s);
                    }
                });

    }

    /**
     * flatMap 转换符
     * flatMap并不保证事件的顺序 要保证顺序使用concatMap
     */
    private static void flatMap() {
        observable
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> list = new ArrayList<String>();
                        for (int i = 0; i < 2; i++) {
                            list.add("flatMap:" + integer);
                        }
                        //.delay(10, TimeUnit.MILLISECONDS)  延时10秒
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, s);
                    }
                });
    }

    /**
     * 模拟实现注册成功后就进行登录
     */
//    private static void flatMapApi() {
//        public interface Api {
//            @GET
//            Observable<LoginResponse> login(@Body LoginRequest request);
//
//            @GET
//            Observable<RegisterResponse> register(@Body RegisterRequest request);
//        }
//
//        api.register(new RegisterRequest())            //发起注册请求
//                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
//                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
//                .doOnNext(new Consumer<RegisterResponse>() {
//                    @Override
//                    public void accept(RegisterResponse registerResponse) throws Exception {
//                        //先根据注册的响应结果去做一些操作
//                    }
//                })
//                .observeOn(Schedulers.io())                 //回到IO线程去发起登录请求
//                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
//                    @Override
//                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
//                        return api.login(new LoginRequest());
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
//                .subscribe(new Consumer<LoginResponse>() {
//                    @Override
//                    public void accept(LoginResponse loginResponse) throws Exception {
//                        Toast.makeText(MusicMainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(MusicMainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
    private static void zip() {
        Observable.zip(observable, observableB, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return "zip:" + s + integer;
            }
        })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, s);
                    }
                });
    }

    /**
     * filter
     * sample
     */
    private static void filter() {
        observableB.subscribeOn(Schedulers.io())
                /**
                 *   filter 操作符 只接收允许符合条件的事件
                 *   这里只允许10的倍数的事件通过
                 */
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return Integer.valueOf(s) % 10 == 0;
                    }
                })
                /**
                 * sample 这个操作符每隔指定的时间就从上游中取出一个事件发送给下游.
                 * 这里是每两秒发送一个事件
                 */
//                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "filter" + s);
                    }
                });
    }

    private static  void Subscriber(){
        flowable.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
//                s.request(Long.MAX_VALUE);          //下游接收的事件数
            }

            @Override
            public void onNext(String s) {

                Log.e(TAG,"onNext" + s);

            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    public static void postNet(){
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(final ObservableEmitter<Response> observableEmitter) throws Exception {
                Api api = getRetrofit().create(Api.class);
                Call<CommentBean> call = api.getCommenList("c1_38b166523382428aa919db4657b7acfa", "10", "1");
                call.enqueue(new Callback<CommentBean>() {
                    @Override
                    public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                        observableEmitter.onNext(response);
                    }

                    @Override
                    public void onFailure(Call<CommentBean> call, Throwable throwable) {

                    }
                });
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        CommentBean bean = (CommentBean) response.body();
                        if (bean.getStatus() == 1){
                            Log.e(TAG,"请求");
                            Api api = getRetrofit().create(Api.class);
                            Call<CommentBean> call = api.getCommenList("c1_38b166523382428aa919db4657b7acfa", "10", "1");
                            call.enqueue(new Callback<CommentBean>() {
                                @Override
                                public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {

                                }

                                @Override
                                public void onFailure(Call<CommentBean> call, Throwable throwable) {

                                }
                            });
                        }
                    }
                }).subscribe(new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {

            }
        });

    }
}
