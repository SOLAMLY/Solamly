package com.example.solamly.solamly.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @Author SOLAMLY
 * @Date 2018/7/19 15:27
 * @Description:
 */

public abstract class BaseFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"--------- onCreateView");

        return inflater.inflate(setLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"--------- onViewCreated");
//        EventBus.getDefault().register(this);

        initView();
        initData();
        setListener();

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"--------- onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"--------- onResume");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"--------- onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"--------- onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"--------- onDestroy");

//        EventBus.getDefault().unregister(this);
    }



    protected abstract int setLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void setListener();

}
