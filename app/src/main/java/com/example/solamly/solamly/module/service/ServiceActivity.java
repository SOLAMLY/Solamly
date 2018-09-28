package com.example.solamly.solamly.module.service;

import android.content.Intent;
import android.view.View;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;

import butterknife.OnClick;

/**
 * @Author SOLAMLY
 * @Date 2018/9/25 15:42
 * @Description:
 */

public class ServiceActivity extends BaseActivity {


    @Override
    protected int setLayout() {
        return R.layout.activity_service;
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
            R.id.btn_service
    })
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_service:
                startService(getIntent());
                break;
        }
    }

    public Intent getIntent(){
        return new Intent(this,TestService.class);
    }
}
