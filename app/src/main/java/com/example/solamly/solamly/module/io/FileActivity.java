package com.example.solamly.solamly.module.io;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author SOLAMLY
 * @Date 2018/9/13 9:56
 * @Description:
 */

public class FileActivity extends BaseActivity {
    @BindView(R.id.et_input)
    EditText editText;

    @Override
    protected int setLayout() {
        return R.layout.activity_file;
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
            R.id.btn_save,
            R.id.btn_read
    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_save:

                break;
            case R.id.btn_read:
        }
    }


}
