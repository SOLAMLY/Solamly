package com.example.solamly.solamly.module.animation;

import android.animation.ValueAnimator;
import android.widget.ImageView;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;

import butterknife.BindView;

/**
 * @Author SOLAMLY
 * @Date 2018/11/17 10:18
 * @Description:
 */
public class AnimationActivity extends BaseActivity {
    @BindView(R.id.animation_imageView)
    ImageView mAnimationImageView;

    @Override
    protected int setLayout() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initView() {
        // 步骤1：设置属性数值的初始值 & 结束值
        ValueAnimator mValueAnimator = ValueAnimator.ofInt(mAnimationImageView.getLayoutParams().width,500,300,1000,50);
        // 步骤2：设置动画的播放各种属性
        mValueAnimator.setDuration(5000);
        mValueAnimator.setStartDelay(1000);
        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获得每次变化后的属性值
                int value = (int) animation.getAnimatedValue();
                //控件跟随着变化
                mAnimationImageView.getLayoutParams().width = value;
                mAnimationImageView.getLayoutParams().height = value;

                //重新绘制,实现动画效果
                mAnimationImageView.requestLayout();
            }
        });

        mValueAnimator.start();


    }

    @Override
    protected void initData() {

    }




}
