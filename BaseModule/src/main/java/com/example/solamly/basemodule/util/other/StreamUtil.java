package com.example.solamly.basemodule.util.other;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Author SOLAMLY
 * @Date 2018/9/6 10:24
 * @Description:
 */

public class StreamUtil {

    public static String getStringByInputStream(InputStream is) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            is.close();
            String result = new String(os.toByteArray());
            if (result.contains("charset=gb2312")) {
                return new String(os.toByteArray(), "gb2312");
            } else {
                return new String(os.toByteArray(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getString(InputStream is) {
        try {
            StringBuilder builder = new StringBuilder();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                builder.append(new String(bytes, Charset.forName("utf-8")));
            }
            is.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

