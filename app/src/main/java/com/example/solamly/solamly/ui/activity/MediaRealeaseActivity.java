package com.example.solamly.solamly.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.basemodule.util.imageloader.BitmapUtil;
import com.example.solamly.basemodule.util.other.MeasurementUtil;
import com.example.solamly.solamly.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @Author SOLAMLY
 * @Date 2018/8/30 15:15
 * @Description:
 */

public class MediaRealeaseActivity extends BaseActivity {
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.status)
    View view;

    private int viewWidth;

    @Override
    protected int setLayout() {
        return R.layout.activity_media_release;
    }

    @Override
    protected void initView() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.height = MeasurementUtil.getStatusBarHeight(this);
    }

    @Override
    protected void initData() {

        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                editText.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                viewWidth = editText.getWidth();
            }
        });

    }


    /**
     * 得到插入图片的SpannableString
     *
     * @param bitmap
     * @param path
     * @return
     */
    public SpannableString getImageSpan(Bitmap bitmap, String path) {
        int[] imageWidthHeight = MeasurementUtil.getImageZoomSize(bitmap, viewWidth);

        SpannableString spannableString = new SpannableString(path);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        drawable.setBounds(0, 0, imageWidthHeight[0], imageWidthHeight[1]);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 0, path.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 插入内容
     *
     * @param ss
     */
    private void insertEditText(SpannableString ss) {
        Log.i("原始数据", editText.getText().toString());
        int index = editText.getSelectionStart();           //得到光标当前的位置
        Editable editable = editText.getText();
        editable.insert(index, ss);
        if (index == editText.getText().length()) {
            editable.insert(editText.getText().length(), "\na");
        }
        editText.setText(editable);
        editText.setSelection(index + ss.length());         //定位光标到最后
        Log.i("插入后数据", editText.getText().toString());
    }

    /**
     * 插入HTML标签
     *
     * @param content
     * @param type    0:段落  1:图片  2:换行  3:段落
     * @return
     */
    public String getLabelText(String content, int type) {
        if (type == 0) {
            return "<p>" + content + "</p>";
        } else if (type == 1) {
            return "<p><img src=\"" + content + "\"/></p>";
        } else if (type == 2) {
            return content + "<br></br>";
        } else if (type == 3) {
            return "<p>" + content + "</p>";
        } else {
            return content;
        }
    }


    /**
     * 初始化图片选择器
     */
    public void intiImageSelector() {
        MultiImageSelector
                .create()
                .showCamera(true)
                .count(9)
                .start(this, REQUEST_IMAGE);
    }

    private static final int REQUEST_IMAGE = 0;
    private List<String> imagePath = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            imagePath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            for (int i = 0; i < imagePath.size(); i++) {
                loadHtmlData(imagePath.get(i));
                //                 Bitmap bitmap = getImageBitmap(imagePath.get(i));
//                SpannableString ss = getImageSpan(bitmap, imagePath.get(i));
//                insertEditText(ss);
//
//           }
            }
        }
    }

    public boolean isPairing(String str) {
        Log.e("检查的字符串",str);
        int headCount = 0;
        int tailCount = 0;
        while (str.contains("<img src")) {
            str = str.replaceFirst("<img src", "");
            headCount ++;
        }
        while (str.contains("\"/>")){
            str = str.replaceFirst("\">", "");
            tailCount++;
        }
        boolean result = headCount ==  tailCount;
        Log.e("检查结果", result + "");
        return result;
    }

    /**
     * 封装成HTML数据
     *
     * @param picUrl
     */
    public void loadHtmlData(String picUrl) {
        String etStr = Html.toHtml(editText.getText());         //获取EditText内容：经过转换后增加了标签 <p dir="ltr"></p>
        //去除EditText自带的标签
        if (!TextUtils.isEmpty(etStr)) {
            etStr = etStr.replace("<p dir=\"ltr\">", "");
            etStr = etStr.substring(0, etStr.length() - 5);
        }
        int index = editText.getSelectionStart();
        Log.e("光标", index + "");
        Log.e("EditText字符长度", editText.getText().length() + "");

        if (index == 0) {
            url = getLabelText(picUrl, 1) + getLabelText(etStr, 2);
        } else if (index == editText.getText().length()) {
            url = getLabelText(etStr, 2) + getLabelText(picUrl, 1);
        } else {
//            String front = etStr.substring(0, index);
//            String later = etStr.substring(index);
//            if (!isPairing(front) || !isPairing(later)){
//                index = later.indexOf("\"/>") + later.length();
//                Log.e("光标",index + "");
//            }
            url = getLabelText(editText.getText().toString().substring(0,index),2) + getLabelText(picUrl,1) + getLabelText(editText.getText().toString().substring(index),2);
            Spannable spannable = new SpannableString(url);
            Log.e("转换",Html.toHtml(spannable));
        }


        Log.e("HTML", "插入图片前的Html:" + etStr + "\n插入图片后的Html:" + url);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            editText.setText(Html.fromHtml(url, imageGetter, null));
        } else {
            editText.setText(Html.fromHtml(url));
        }
        editText.setSelection(editText.getText().length());
    }


    private String url;

    @OnClick({
            R.id.seletced_image,
            R.id.yulan
    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.seletced_image:
                intiImageSelector();
                break;
            case R.id.yulan:
                Bundle bundle = new Bundle();
                bundle.putString("URL", url);
                skip(MediaActivity.class, bundle);
                break;
        }
    }

    /**
     * 显示网络图片
     */
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable;
            try {
                URL url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }
    };

    /**
     * 显示本地图片
     */
    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            drawable = Drawable.createFromPath(source);
            Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
            int[] bitmapSize = MeasurementUtil.getImageZoomSize(bitmap, viewWidth);
            drawable.setBounds(0, 0, bitmapSize[0], bitmapSize[1]);
            return drawable;
        }
    };

}
