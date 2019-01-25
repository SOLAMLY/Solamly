package com.example.solamly.solamly.ui.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.basemodule.util.other.MeasurementUtil;
import com.example.solamly.solamly.R;

import butterknife.BindView;

/**
 * @Author SOLAMLY
 * @Date 2018/8/16 13:41
 * @Description:
 */

public class MRichEditorActivity extends BaseActivity {

    @BindView(R.id.view)
    View view;
    @Override
    protected int setLayout() {
        return R.layout.activity_mrich_editor;
    }

    @Override
    protected void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = MeasurementUtil.getStatusBarHeight(this);
    }

    @Override
    protected void initData() {

    }

}
