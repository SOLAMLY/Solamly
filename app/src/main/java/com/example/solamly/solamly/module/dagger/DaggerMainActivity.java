package com.example.solamly.solamly.module.dagger;

import android.util.Log;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

import static com.example.solamly.basemodule.base.ui.BaseConstant.TAG_DAGGER;

/**
 * @Author SOLAMLY
 * @Date 2018/9/12 13:11
 * @Description:
 */

public class DaggerMainActivity extends BaseActivity {
  @Inject
  OkHttpClient okHttpClient;

    @Override
    protected int setLayout() {
        return R.layout.activity_dagger;
    }

    @Override
    protected void initView() {

//      DaggerHttpComponse.builder().build().inject(this);
      Log.i(TAG_DAGGER,okHttpClient.hashCode() + "");

    }

    @Override
    protected void initData() {

    }


}
