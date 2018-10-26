package com.example.solamly.basemodule.util.other;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by SOLAMLY on 2018/5/23.
 */

public class WebViewClientUtil extends WebViewClient {

    private WebView webView;

    public WebViewClientUtil(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        view.loadUrl("javascript:try{autoplay();}catch(e){}");
        imgReset();
        handleBlankLink();
        addImageClickListner();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }


    private void imgReset() {
        webView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName('img'); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "var img = objs[i];   "
                + "    img.style.width = '100%';   "
                + "    img.style.height = 'auto';   "
                + "}" + "})()");
    }

    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    private void handleBlankLink() {
        webView.loadUrl("javascript:" +
                "var allLinks = document.getElementsByTagName('a');" +
                "if(allLinks){" +
                "var i;" +
                "for(i=0,i<allLinks.length,i++){" +
                "var link = allLinks[i];" +
                "var target = link.getAttribute('href');" +
                "if(target && target == ''){" +
                "target.style.display = 'none';" +
                "}" +
                "}" +
                "}");

        webView.loadUrl("javascript:" +
                "var allLinks = document.getElementsByTagName('img');" +
                "if(allLinks){" +
                "var i;" +
                "for(i=0,i<allLinks.length,i++){" +
                "var link = allLinks[i];" +
                "var target = link.getAttribute('href');" +
                "if(target && target == ''){" +
                "target.style.display = 'none';" +
                "}" +
                "}" +
                "}");
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        view.getSettings().setJavaScriptEnabled(true);
        super.onPageStarted(view, url, favicon);
    }

}
