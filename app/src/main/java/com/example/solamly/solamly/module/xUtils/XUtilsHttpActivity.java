package com.example.solamly.solamly.module.xUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @Author: SOLAMLY
 * @Date: 2019/4/8 0008 14:34
 * @Description:
 */
@ContentView(R.layout.activity_xutils_http)
public class XUtilsHttpActivity extends AppCompatActivity {
    @ViewInject(R.id.tv_name)
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mTextView.setText("jahlsdfj");
    }
}
