package com.example.solamly.solamly.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solamly.basemodule.util.other.MeasurementUtil;
import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

import butterknife.BindView;

/**
 * @Author SOLAMLY
 * @Date 2018/11/21 17:53
 * @Description:
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.tv_100dp)
    TextView mTv100dp;

    private Context mContext;
    @Override
    protected int setLayout() {
        return R.layout.activity_custom_layout;
    }

    @Override
    protected void initView() {
        mContext = this;

        Log.i("直接获取的宽高" , mTv100dp.getBottom() + ":" + mTv100dp.getTop());
        mTv100dp.post(new Runnable() {
            @Override
            public void run() {
                Log.i("放到post里的宽高" , mTv100dp.getBottom() + " :" + mTv100dp.getTop());
                Log.i("放到post里的宽高 - 转换后的" , MeasurementUtil.px2dip(mContext,mTv100dp.getBottom()) + " :" +MeasurementUtil.dip2px(mContext,mTv100dp.getTop()));
            }
        });

        mTv100dp.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTv100dp.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.i("使用ViewTree获取的宽高" , mTv100dp.getBottom() + " :" + mTv100dp.getTop());
                Log.i("使用ViewTree获取的宽高 - 转换后的" , MeasurementUtil.px2dip(mContext,mTv100dp.getBottom()) + " :" +MeasurementUtil.dip2px(mContext,mTv100dp.getTop()));

            }
        });





        ImageView mImageView = (ImageView) findViewById(R.id.image);


        Bitmap mBgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ui_bg_generalize_poster);

        Drawable mDrawable = getResources().getDrawable(R.drawable.ui_shape_circle_white);

        int circleWidth = mBitmap.getWidth() * 102 / 294;         //白色圆形背景宽度
        Bitmap whiteBitmap = getWhiteCircleBgBitmap(mDrawable, circleWidth, circleWidth);


        int top = mBgBitmap.getHeight() * 335 / 523;
        Bitmap mBitmap1 = combineBitmap(mBgBitmap, whiteBitmap,  top);

        int widthQr = mBgBitmap.getWidth() * 90 / 294;
        int top1 = mBitmap1.getHeight() * 340 / 523;

        mBitmap = getQrCodeImage(widthQr, widthQr, "http://www.51jxc.cn/download/kdb.html?FromName=KCode&FromValues=13192294609");

        Bitmap mBitmap2 = combineBitmap(mBitmap1, mBitmap,  top1);
        mImageView.setImageBitmap(mBitmap2);
    }

    /**
     * Drawable ->  Bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap getWhiteCircleBgBitmap(Drawable drawable, int width, int height) {
        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return mBitmap;
    }

    /**
     * 合并Bitmap
     *
     * @param background 背景图
     * @param foreground
     * @param left       foreground生成时的左边距
     * @param top        foreground生成时的上边距
     * @return
     */
    public static Bitmap combineBitmap(Bitmap background, Bitmap foreground,  int top) {
        int left = (background.getWidth() - foreground.getWidth()) / 2;
        Bitmap newmap = Bitmap.createBitmap(background.getWidth(), background.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newmap);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(foreground, left, top, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return newmap;
    }


    Bitmap mBitmap;

    /**
     * 生成QR图
     *
     * @param QR_WIDTH
     * @param QR_HEIGHT
     * @param text
     * @return
     * @throws WriterException
     */
    public static Bitmap getQrCodeImage(int QR_WIDTH, int QR_HEIGHT, String text) {
        Bitmap bitmap = null;
//        try {
//            // 需要引入core包
//            QRCodeWriter writer = new QRCodeWriter();
//            if (text == null || "".equals(text) || text.length() < 1) {
//                return null;
//            }
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
//            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
//            for (int y = 0; y < QR_HEIGHT; y++) {
//                for (int x = 0; x < QR_WIDTH; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * QR_WIDTH + x] = 0xff000000;
//                    } else {
////                    pixels[y * QR_WIDTH + x] = 0xffffffff;// 解决保存之后一片黑的bug
//                    }
//                }
//            }
//
//            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
        return bitmap;
    }


    @Override
    protected void initData() {

    }



}
