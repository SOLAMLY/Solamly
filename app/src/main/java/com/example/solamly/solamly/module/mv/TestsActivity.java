package com.example.solamly.solamly.module.mv;

import com.example.solamly.basemodule.base.ui.BaseTestActivity;
import com.example.solamly.basemodule.util.FragmentControlManager;
import com.example.solamly.solamly.R;


/**
 * @Author SOLAMLY
 * @Date 2018/11/21 17:53
 * @Description:
 */
public class TestsActivity extends BaseTestActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_tests;
    }

    @Override
    protected void initView() {
        super.initView();
        MyViewModelFragment1 mMyViewModelFragment1=   new MyViewModelFragment1();
        FragmentControlManager mFragmentControlManager = new FragmentControlManager();
        mFragmentControlManager.init(this,R.id.frame_layout);
        mFragmentControlManager.showSingle(mMyViewModelFragment1);
//        mFragmentControlManager.hidePage(mMyViewModelFragment1);
//        mFragmentControlManager.showSingle(new MyViewModelFragment2());
    }



}
