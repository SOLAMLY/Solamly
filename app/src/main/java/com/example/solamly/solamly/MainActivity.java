package com.example.solamly.solamly;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.module.AsyncTask.AsyncTaskActivity;
import com.example.solamly.solamly.module.XmlAnalysis.JsonXmlActivity;
import com.example.solamly.solamly.module.animation.AnimationActivity;
import com.example.solamly.solamly.module.baidu_map.BaiduMapActivity;
import com.example.solamly.solamly.module.dagger.DaggerMainActivity;
import com.example.solamly.solamly.module.design_mode.factory.FactoryActivity;
import com.example.solamly.solamly.module.download.DownloadActivity;
import com.example.solamly.solamly.module.greendao.GreenDaoActivity;
import com.example.solamly.solamly.module.handler.HandlerMainActivity;
import com.example.solamly.solamly.module.network.network.HttpActivity;
import com.example.solamly.solamly.module.network.rxjava_retrofit.RxJavaMainActivity;
import com.example.solamly.solamly.module.service.ServiceActivity;
import com.example.solamly.solamly.ui.activity.CustomBehaviorActivity;
import com.example.solamly.solamly.ui.activity.HorizonRecycleviewActivity;
import com.example.solamly.solamly.ui.activity.MediaRealeaseActivity;
import com.example.solamly.solamly.ui.activity.TestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main)
    LinearLayout relativeLayout;
    private Button button;
    RelativeLayout.LayoutParams layoutParams;
    private List<String> views = new ArrayList<>();
    private final String TAG = this.getClass().getName();

    private String[] mStringNames = new String[]{
            "自定义Behavior实现Android知乎首页",
            "JSON + XML 解析",
            "富文本",
            "GreenDao",
            "HttpClient",
            "RxJava",
            "Dagger",
            "Handler",
            "AsyncTask",
            "Service",
            "Factory",
            "百度地图",
            "断点续传",
            "属性动画",
            "布局测试",
            "横线指示器"
    };

    private List<Class> mActivityClass = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        for (int i = 0; i < mStringNames.length; i++) {
            button = new Button(this);
            button.setText(getTextName(i));
            button.setBackgroundResource(R.drawable.bg_code_btn);
            button.setTextSize(15f);
            button.setPadding(10, 10, 10, 10);

            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20, 10, 0, 0);
            button.setLayoutParams(layoutParams);

            relativeLayout.addView(button);
        }
        setListener();
    }

    @Override
    protected void initData() {

    }

    protected void setListener() {

        mActivityClass.add(CustomBehaviorActivity.class);
        mActivityClass.add(JsonXmlActivity.class);
        mActivityClass.add(MediaRealeaseActivity.class);
        mActivityClass.add(GreenDaoActivity.class);
        mActivityClass.add(HttpActivity.class);
        mActivityClass.add(RxJavaMainActivity.class);
        mActivityClass.add(DaggerMainActivity.class);
        mActivityClass.add(HandlerMainActivity.class);
        mActivityClass.add(AsyncTaskActivity.class);
        mActivityClass.add(ServiceActivity.class);
        mActivityClass.add(FactoryActivity.class);
        mActivityClass.add(BaiduMapActivity.class);
        mActivityClass.add(DownloadActivity.class);
        mActivityClass.add(AnimationActivity.class);
        mActivityClass.add(TestActivity.class);
        mActivityClass.add(HorizonRecycleviewActivity.class);


        for (int i = 0; i < mStringNames.length; i++) {
            final int finalI = i;
            relativeLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skip(mActivityClass.get(finalI), null);
                }
            });
        }
    }

    public String getTextName(int i) {

        for (int j = 0; j < mStringNames.length; j++) {
            if (j == i) {
                return mStringNames[j];
            }
        }
        return "";
    }

    @Override
    public void onClick(View view) {

    }
}
