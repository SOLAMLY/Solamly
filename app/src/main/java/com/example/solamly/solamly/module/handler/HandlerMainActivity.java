package com.example.solamly.solamly.module.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.basemodule.util.imageloader.BitmapUtil;
import com.example.solamly.solamly.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.solamly.basemodule.util.imageloader.BitmapUtil.drawableToBitmap;


/**
 * @Author SOLAMLY
 * @Date 2018/9/19 11:09
 * @Description:
 */

public class HandlerMainActivity extends BaseActivity {
    @BindView(R.id.imageView)
    ImageView imageView;

    private Context context = this;
    private int w, h;

    // TODO: 2018/9/19 Handler引起的内存泄露
    private  Handler handler = new Handler(new Handler.Callback() {
        /**
         * Callback 用于拦截发送过来的信息
         * @param msg
         * @return true:拦截 - 输出语句不执行   false:不拦截
         */
        @Override
        public boolean handleMessage(Message msg) {
            Log.e("拦截", "arg2=" + msg.arg1);
            return true;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("输出", "msg.arg1" + msg.arg1);
        }
    };
    private int[] images = {R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};
    private Bitmap[] bitmaps = new Bitmap[4];
    private int index;

    private MyRunnable myRunnable = new MyRunnable();

     class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 4;
            imageView.setImageBitmap(bitmaps[index]);
            handler.postDelayed(myRunnable, 1000);
            Log.e("循环",index + "");
        }
    }



    @Override
    protected int setLayout() {
        return R.layout.activity_handler_main;
    }

    @Override
    protected void initView() {

        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                for (int i = 0; i < images.length; i++) {
                    Bitmap bitmap = drawableToBitmap(context, images[i]);
                    float scale = BitmapUtil.getBitmapScale(bitmap, imageView.getWidth(), imageView.getHeight());
                    bitmaps[i] = BitmapUtil.getScaleBitmapForBit(bitmap, scale);
                }
                handler.postDelayed(myRunnable, 1000);
            }
        });

        /**
         * 两者等价
         * Message message = new Message();
         * Message message = handler.obtainMessage();
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = handler.obtainMessage();
                message.arg1 = 100;
                handler.sendMessage(message);
            }
        }).start();


    }

    @Override
    protected void initData() {

    }



    private boolean isClick = true;

    @Override
    @OnClick({
            R.id.button
    })
    public void onClick(View view) {
        if (isClick) {
            //移除runnable，停止播放
            handler.removeCallbacks(myRunnable);
            isClick = false;
        } else {
            //开始播放
            handler.postDelayed(myRunnable, 0);
            isClick = true;
        }
    }

    /**
     * 需要在页面销毁时同时销毁Runnble和Handler ，否则可能会引起内存泄露
     * 如本例子，在Runnable中一直循环发送，若不销毁，则在该页面finish() 后还是会一直循环send
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myRunnable != null) {
            myRunnable = null;
            handler.removeCallbacksAndMessages(myRunnable);
        }
    }
}
