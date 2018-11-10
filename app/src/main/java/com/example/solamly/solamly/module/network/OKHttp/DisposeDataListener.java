package com.example.solamly.solamly.module.network.OKHttp;

/**
 * @Author SOLAMLY
 * @Date 2018/9/7 17:26
 * @Description:
 */

public interface DisposeDataListener {
    //请求成功回调事件处理
    public void onSuccess(Object responseObj);

    //请求失败回调事件处理
    public void onFailure(Object responseObj);
}
