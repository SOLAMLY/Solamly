package com.example.solamly.basemodule.util.other;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @Author SOLAMLY
 * @Date 2018/7/25 21:00
 * @Description:
 */

public class GsonUtil {
    private static Gson gson = new Gson();


    /**
     * 将一个Object对象转换为 json 格式
     *
     * @param object
     * @return
     */
    public static String getJsonFormBean(Object object) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(object);
        return jsonStr;
    }

    /**
     *  将一段Json 转换为 javaBean
     * @param json json数据
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBeanFromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }


}
