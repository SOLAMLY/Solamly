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

        int circleWidth = mBgBitmap.getWidth() * 102 / 294;         //白色圆形背景宽度
        Bitmap whiteBitmap = getWhiteCircleBgBitmap(mDrawable, circleWidth, circleWidth);


        int top = mBgBitmap.getHeight() * 335 / 523;
        Bitmap mBitmap1 = combineBitmap(mBgBitmap, whiteBitmap,  top);

        mImageView.setImageBitmap(mBitmap1);
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
     * @param top        foreground生成时的上边距
     * @return
     */
    public static Bitmap combineBitmap(Bitmap background, Bitmap foreground,  int top) {
        int left = (background.getWidth() - foreground.getWidth()) / 2;
        /* 问题： 两张bitmap合成后会出现错位(在另一个demo里测试时不会，在这个项目则出现了错位)
         * 解决方法：将Bitmap.createBitmap(width,height,Bitmap.Config) (怀疑是这个方法引起的) 替换成  Bitmap.createBitmap(bitmap,x,y,width,height) [合成后不会错位]
         * 问题分析：替换成新的方法后直接使用new Canvas(newmap) 会出现个异常 IllegalStateException:Immutable bitmap passed to Canvas constructor
         * 得到的结论应该:因为不能直接修改res文件,导致了Bitmap.createBitmap(width,height,Bitmap.Config) 获取的bitmap有问题，所以出现了错位的问题
         *
         */
//        Bitmap newmap = Bitmap.createBitmap(background.getWidth(),background.getHeight(),Bitmap.Config.ARGB_8888);
        Bitmap newmap = Bitmap.createBitmap(background,0,0,background.getWidth(),background.getHeight());
        Bitmap copyBitmap = newmap;
        if(!copyBitmap.isMutable()){
            copyBitmap = newmap.copy(Bitmap.Config.RGB_565, true);
        }
        Canvas canvas = new Canvas(copyBitmap);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(foreground, left, top, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return copyBitmap;
    }



    @Override
    protected void initData() {

    }



}
