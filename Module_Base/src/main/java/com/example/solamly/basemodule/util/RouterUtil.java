package com.example.solamly.basemodule.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.solamly.basemodule.util.other.ToastUtil;

import java.util.Iterator;
import java.util.Stack;


public class RouterUtil {

    public static Stack<Activity> activityStack = new Stack<>();

    public static void addActivity(Activity activity) {
        if (activityStack != null) {
            activityStack.add(activity);
        }
    }

    static Activity activity = null;

    /**
     * 获取当前显示Activity（堆栈中最后一个传入的activity）
     */
    public static Activity getCurrentActyvity() {
        if (activityStack != null) {
            activity = activityStack.lastElement();
            return activity;
        } else {
            return activity;
        }
    }

    public static Activity getActivity(Class<?> className) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(className)) {
                    return activity;
                }
            }
        }
        return null;
    }


    /**
     * 获取某个Activity
     * @param className
     * @return
     */
    public static Activity getActivityByName(String className) {
        if (activityStack != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity a = activityStack.get(i);
                if (a.getClass().getName().equals(className)) {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * 回退到某个Activity
     * @param cla
     */
    public static void skipActivityByClass(Class<?> cla) {
        if (activityStack != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity a = activityStack.get(i);
                if (a.getClass().getName().equals(cla.getName())) {
                    return;
                }
                a.finish();
            }
        }
    }


    /**
     * 跳转
     * @param cla
     */
    public static void skipActivity(Class<?> cla) {
        skipActivity(cla, null);
    }

    public static void skipActivity(Class<?> cla, Bundle bundle) {
        Activity context = getCurrentActyvity();
        if (context != null) {
            Intent intent = new Intent(context, cla);
            if (bundle != null)
                intent.putExtras(bundle);
            context.startActivity(intent);
//            context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    public static void skipActivityForResult(Class<?> cla, Bundle bundle, int requestCode) {
        Activity context = getCurrentActyvity();
        if (context != null) {
            Intent intent = new Intent(context, cla);
            if (bundle != null)
                intent.putExtras(bundle);
            context.startActivityForResult(intent, requestCode);
//            context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    /**
     * 结束除当前传入以外所有Activity
     */
    static Iterator<Activity> it;

    public static void finishOthersActivity(Activity activity) {
        if (activityStack != null) {
            //stack不可在for循环中更改
            it = activityStack.iterator();
            while (it.hasNext()) {
                Activity Activity = it.next();
                if (!Activity.getClass().equals(activity.getClass())) {
                    it.remove();
                    if (!Activity.isFinishing()) {
                        Activity.finish();
                    }
                }
            }
        }
    }

    public static void finishOthersActivity(Class<?> cls) {
        if (activityStack != null) {
            it = activityStack.iterator();
            while (it.hasNext()) {
                Activity Activity = it.next();
                if (!Activity.getClass().equals(cls)) {
                    it.remove();
                    if (!Activity.isFinishing()) {
                        Activity.finish();
                    }
                }
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        if (activityStack != null) {
            it = activityStack.iterator();
            while (it.hasNext()) {
                Activity Activity = it.next();
                if (!Activity.getClass().equals(activity.getClass())) {
                    it.remove();
                    if (!Activity.isFinishing()) {
                        Activity.finish();
                    }
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public static void exit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityMgr != null)
                activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
//            e.printStackTrace();
//            android.os.Process.killProcess(android.os.Process.myPid());// 杀死该应用进程
            System.exit(0);
        }
    }

    /* - - - - - - - - - - - - - - - - - 跳转到具体页面 START - - - - - - - - - - - - - - - - - - */

    /**
     * 跳转到微信App
     */
    private static void routerWechatApp(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtil.toast("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }


    /**
     * 跳转QQ 好友聊天界面
     * @param context
     * @param qqNum qq号码
     */
    public static void routerQQApp(Context context, String qqNum) {
        try {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum;//uin是发送过去的qq号码
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            ToastUtil.toast("检查到您手机没有安装QQ，请安装后使用该功能");
        }
    }
}
