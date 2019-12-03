package com.example.solamly.solamly.module.mv;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @Author: SOLAMLY
 * @Date: 2019/9/19 0019 15:46
 * @Description:
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> mLiveData = new MutableLiveData<>();

    public void setLiveData(String msg){
        mLiveData.setValue(msg);
    }

    public MutableLiveData<String> getLiveData() {
        return mLiveData;
    }
}
