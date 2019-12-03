package com.example.solamly.basemodule.base.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.solamly.basemodule.R;
import com.example.solamly.basemodule.util.RouterUtil;
import com.example.solamly.basemodule.util.eventbus.EventBusMessage;
import com.example.solamly.basemodule.util.eventbus.EventBusUtil;
import com.example.solamly.basemodule.util.other.StatusBarUtil;
import com.example.solamly.basemodule.util.other.Utils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @Author: SOLAMLY
 * @Date: 2019/10/29 0029 10:35
 * @Description:
 */
public abstract class NewBaseActivity<VD extends ViewDataBinding> extends AppCompatActivity implements View.OnClickListener {
    public VD mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, setLayoutId());
        StatusBarUtil.setStatusBarImmerse(this);

        initTitleBar();
        init();
        EventBusUtil.register(this);
        RouterUtil.addActivity(this);
    }

    public abstract int setLayoutId();

    public abstract String setTitle();

    public abstract void init();


    private void initTitleBar() {
        RelativeLayout mLinearLayout = mBinding.getRoot().findViewById(R.id.title_bar);
        if (mLinearLayout != null) {
            mLinearLayout.findViewById(R.id.iv_back_title_bar).setOnClickListener(this);
            TextView title = mLinearLayout.findViewById(R.id.tv_title_title_bar);
            title.setText(Utils.getContent(setTitle()));
            StatusBarUtil.setStatusBarPadding(mLinearLayout);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back_title_bar) {
            finish();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
        RouterUtil.finishActivity(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public <T> void onEvent(EventBusMessage<T> message) {

    }

}
