package com.example.solamly.solamly.module.aspectJ;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.basemodule.base.ui.NewBaseActivity;
import com.example.solamly.basemodule.constant.Constant;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.databinding.ActivityAsepectTestBinding;
import com.orhanobut.logger.Logger;

import org.aspectj.lang.annotation.Aspect;

/**
 * @Author: SOLAMLY
 * @Date: 2019/12/30 0030 10:51
 * @Description:
 */
public class AspectTestActivity extends NewBaseActivity<ActivityAsepectTestBinding> {
    @Override
    public int setLayoutId() {
        return R.layout.activity_asepect_test;

    }

    @Override
    public String setTitle() {
        return "AspectJ Test";
    }

    @Override
    public void init() {
        mBinding.setOnClick(this);
    }

    @DeBugLog
    @Override
    public void onClick(View v) {
        super.onClick(v);
        TextView mTextView = null;
        mTextView.setText("");
        Logger.i("哈哈哈哈哈哈哈");
    }
}
