package com.example.solamly.basemodule.util.eventbus;

import org.greenrobot.eventbus.EventBus;


/**
 * @Author: SOLAMLY
 * @Date: 2019/9/20 0020 15:46
 * @Description:
 */
public class EventBusUtil {
    //注册事件
    public static void register(Object context) {
        if (!EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().register(context);
        }
    }
    //解除
    public static void unregister(Object context) {
        if (EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().unregister(context);
        }
    }
    //发送消息
    public static <T> void  post(EventBusMessage<T> message) {
        EventBus.getDefault().post(message);
    }
}
