package com.example.solamly.solamly.module.rxjava_retrofit;

import android.view.View;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.module.rxjava.RxJavaUtil;

import butterknife.OnClick;

import static com.example.solamly.solamly.module.rxjava_retrofit.RequestUtil.getCommentLiat;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 10:05
 * @Description:
 */

public class RxJavaMainActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_rxjava_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({
            R.id.btn_net_request
    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btn_net_request:
                getCommentLiat();
                break;
        }
    }

    private void initRxJava(){
        RxJavaUtil.send();
//        accept();
//        map();
//        flatMap();
//        zip();
//        filter();
//        Subscriber();
        RxJavaUtil.postNet();
    }

}
