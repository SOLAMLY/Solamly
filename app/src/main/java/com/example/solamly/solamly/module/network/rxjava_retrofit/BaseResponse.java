package com.example.solamly.solamly.module.network.rxjava_retrofit;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 15:42
 * @Description:
 */

public class BaseResponse<T> {
    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
