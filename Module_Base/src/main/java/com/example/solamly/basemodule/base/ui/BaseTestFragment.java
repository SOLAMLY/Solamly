package com.example.solamly.basemodule.base.ui;

import com.example.solamly.basemodule.R;
import com.example.solamly.basemodule.databinding.LayoutBaseTestBinding;

/**
 * @Author: SOLAMLY
 * @Date: 2019/9/19 0019 16:21
 * @Description:
 */
public  class BaseTestFragment extends BaseBindingFragment {
    protected LayoutBaseTestBinding mBinding;
    @Override
    protected int setLayoutId() {
        return   R.layout.layout_base_test;
    }


    @Override
    protected void initView() {
        mBinding  = (LayoutBaseTestBinding) mDataBinding;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
