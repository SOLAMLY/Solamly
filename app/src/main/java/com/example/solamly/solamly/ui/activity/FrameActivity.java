package com.example.solamly.solamly.ui.activity;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.ui.fragment.AFragment;
import com.example.solamly.solamly.ui.fragment.BFragment;

/**
 * @Author SOLAMLY
 * @Date 2018/8/1 10:19
 * @Description:
 */

public class FrameActivity extends BaseActivity {
    AFragment aFragment = new AFragment();
    BFragment bFragment = new BFragment();
    @Override
    protected int setLayout() {
        return R.layout.fragment;
    }

    @Override
    protected void initView() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_1,aFragment);
//        transaction.replace(R.id.frame_2,bFragment);
//        transaction.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
