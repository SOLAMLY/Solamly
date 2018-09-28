package com.example.solamly.solamly.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.Util.MeasurementUtil;
import com.example.solamly.solamly.Util.WebViewClientUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @Author SOLAMLY
 * @Date 2018/8/30 14:11
 * @Description:
 */

public class MediaActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.status)
    View view;
    @BindView(R.id.text)
    TextView textView;
    private WebViewClientUtil webViewClientUtil;

    private String url = "&nbsp&nbsp&nbsp&nbsp这是文" +
            "<p>" +
            "这是&nbsp&nbsp&nbsp&nbsp第二行\n" +
            "</p>" +
            "<p>" +
            "  <img src=\"https://n.sinaimg.cn/sinacn/20171114/d834-fynstfh8381679.jpg\">" +
            "</p><p>" +
            "这是第三行" +
            "</p>";
    @Override
    protected int setLayout() {
        return R.layout.activity_media;
    }

    @Override
    protected void initView() {
        if (getIntent().getExtras()!= null){
            url = getIntent().getExtras().getString("URL");
        }
        Log.i("URL",url);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.height = MeasurementUtil.getStatusBarHeight(this);

        webViewClientUtil = new WebViewClientUtil(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webView.getSettings().setSupportZoom(false);
        webView.setWebViewClient(webViewClientUtil);
        webView.loadDataWithBaseURL(null,url,"text/html","utf-8",null);

//        textView.setText(Html.fromHtml(url));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    public void intiImageSelector(){
        MultiImageSelector
                .create()
                .showCamera(true)
                .count(9)
                .multi()
                .single()
                .start(this,REQUEST_IMAGE);
    }

    private  static final int REQUEST_IMAGE = 0;
    private  List<String > imagePath = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE){
               imagePath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (int i = 0; i < imagePath.size(); i++) {
                    Log.e("TAG", imagePath.get(i));
            }
        }
    }

    @OnClick(R.id.seletced_image)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.seletced_image:
                intiImageSelector();
                break;
        }
    }
}
