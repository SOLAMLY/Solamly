package com.example.solamly.solamly.ui.fragment;

import android.os.Environment;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solamly.basemodule.base.ui.BaseFragment;
import com.example.solamly.solamly.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author SOLAMLY
 * @Date 2018/7/31 11:10
 * @Description:
 */

public class Fragments extends BaseFragment {
    private TextView textView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment;
    }

    @Override
    protected void initView() {
        textView = (TextView) getActivity().findViewById(R.id.fragment_text);

    }

    @Override
    protected void initData() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGifImage("https://oss.meibbc.com/BeautifyBreast/file/health/1535350179459.gif");
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @WorkerThread
     void saveGifImage(final String uimageUl) {
        try {
            URL url = new URL(uimageUl);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            int contentLength = connection.getContentLength();
            Log.e("TAG", "contentLength:" + contentLength);
            String dirName = Environment.getExternalStorageDirectory() + "/image/";
            File file = new File(dirName);
            if (!file.exists()) {
                file.mkdir();
            }
            String fileName = dirName + System.currentTimeMillis() + ".gif";
            File gifFile = new File(fileName);
            byte[] b = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(gifFile);
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            Log.e("TAG", "GIF保存成功");
            show();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        Toast.makeText(getActivity(), "保存图片成功", Toast.LENGTH_SHORT).show();
    }
}
