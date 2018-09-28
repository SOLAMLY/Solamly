package com.example.solamly.solamly;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.module.AsyncTask.AsyncTaskActivity;
import com.example.solamly.solamly.module.XmlAnalysis.JsonXmlActivity;
import com.example.solamly.solamly.module.dagger.DaggerMainActivity;
import com.example.solamly.solamly.module.design_mode.factory.FactoryActivity;
import com.example.solamly.solamly.module.greendao.GreenDaoActivity;
import com.example.solamly.solamly.module.handler.HandlerMainActivity;
import com.example.solamly.solamly.module.http_url_client.HttpActivity;
import com.example.solamly.solamly.module.rxjava_retrofit.RxJavaMainActivity;
import com.example.solamly.solamly.module.service.ServiceActivity;
import com.example.solamly.solamly.ui.activity.CustomBehaviorActivity;
import com.example.solamly.solamly.ui.activity.MediaRealeaseActivity;

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

    private String[] length = new String[]{
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
            "Factory"
    };
    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        for (int i = 0; i < length.length; i++) {
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

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setListener() {
        relativeLayout.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(CustomBehaviorActivity.class, null);
            }
        });
        relativeLayout.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(JsonXmlActivity.class, null);
            }
        });
        relativeLayout.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(MediaRealeaseActivity.class, null);
            }
        });
        relativeLayout.getChildAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(GreenDaoActivity.class, null);
            }
        });
        relativeLayout.getChildAt(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(HttpActivity.class, null);
            }
        });
        relativeLayout.getChildAt(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(RxJavaMainActivity.class, null);
            }
        });
        relativeLayout.getChildAt(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(DaggerMainActivity.class, null);
            }
        });
        relativeLayout.getChildAt(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(HandlerMainActivity.class, null);
            }
        });
        relativeLayout.getChildAt(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(AsyncTaskActivity.class, null);
            }
        });
        relativeLayout.getChildAt(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(ServiceActivity.class, null);
            }
        });    relativeLayout.getChildAt(10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(FactoryActivity.class, null);
            }
        });
    }
    

    public String getTextName(int i) {

        for (int j = 0; j < length.length; j++) {
            if (j == i){
                return length[j];
            }
        }
        return "";
    }

    @Override
    public void onClick(View view) {

    }
}
