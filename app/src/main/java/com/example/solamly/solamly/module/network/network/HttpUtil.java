package com.example.solamly.solamly.module.network.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.example.solamly.basemodule.util.other.MyLog;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.solamly.basemodule.util.other.StreamUtil.getString;


/**
 * @Author SOLAMLY
 * @Date 2018/9/6 9:29
 * @Description:
 */

public class HttpUtil {


    private static final String TAG = "HTTP_URL_CONNECTION";
    public static final String METHOD_GET = "METHOD_GET";               //GET请求
    public static final String METHOD_POST = "METHOD_POST";             //POST请求

    public static void setUrlConnection(final String url, final String type, final HashMap<String, String> map) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {
                    URL mUrl = new URL(url);
                    connection = (HttpURLConnection) mUrl.openConnection();
                    connection.setConnectTimeout(5000);             //设置链接超时时间
                    connection.setReadTimeout(5000);                //设置读取超时时间
                    connection.addRequestProperty("Connection", "Keep-Alive");//设置与服务器保持连接
                    connection.addRequestProperty("Charset", "UTF-8");//设置字符编码类型
                    connection.setRequestProperty("Connection", "application/json");  // 设定传送的内容类型是可序列化的java对象、(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)

                    /**
                     * GET请求
                     */
                    if (METHOD_GET.equals(type)) {
                        connection.setRequestMethod("GET");            // 设定请求的方法为"POST"，默认是GET

                        /**
                         * POST请求
                         */
                    } else if (METHOD_POST.equals(type)) {
                        connection.setRequestMethod("POST");            // 设定请求的方法为"POST"，默认是GET
                        connection.setUseCaches(false);                 // Post 请求不能使用缓存
                        connection.setDoInput(true);                    //设置是否从httpUrlConnection读入，默认情况下是true;
                        connection.setDoOutput(true);                        //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;

                        setParams(connection.getOutputStream(), map);
                        getMsg(connection);
                    }

                    int code = connection.getResponseCode();
                    String result = getString(connection.getInputStream());
                    Log.e(TAG, "返回Code:" + code + "\n返回Result:" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    /**
     * POST 拼接参数
     *
     * @param os
     * @param map
     */
    // TODO: 2018/9/6 是否考虑使用SparseArray 进行优化
    public static String setParams(OutputStream os, HashMap<String, String> map) {
        try {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(builder)) {
                    builder.append("&");
                }
                builder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            // TODO: 2018/9/6 需要搞清楚各种流的使用
            /**
             * @Description: DataOutputStream 两种都可以进行拼接
             * @CreateData 2018/9/6  14:52
             * @Problem: BufferedWriter 不能正常拼接 http://xxx.html 这种形式的请求、可以拼接 http://xxxx.html?behavior  形式的请求
             * @Solution:
             */
            os.write(builder.toString().getBytes());
            Log.e(TAG, "params:" + builder.toString());
//            DataOutputStream dos = new DataOutputStream(os);
//            dos.write(builder.toString().getBytes());
//            dos.flush();
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//            bw.write(builder.toString());
//            bw.flush();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 请求URL
     *
     * @param client
     */
    // TODO: 2018/9/6 参数怎么获取 
    public static void getMsg(HttpURLConnection client) {
        try {
            MyLog.e("发送请求", "\nUrl:" + client.getURL()
                    + "\nMethod:" + client.getRequestMethod()
                    + "\nResponseMessage:" + client.getResponseMessage()
                    + "\nType:" + client.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Bitmap loadIBitmap(String url){
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bitmap = BitmapFactory.decodeStream(bis);
            is.close();
            bis.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
