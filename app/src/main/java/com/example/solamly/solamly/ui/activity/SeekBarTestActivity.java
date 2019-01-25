package com.example.solamly.solamly.ui.activity;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;
import com.xw.repo.BubbleSeekBar;

import butterknife.BindView;

/**
 * @Author SOLAMLY
 * @Date 2018/10/30 13:56
 * @Description:
 */

public class SeekBarTestActivity extends BaseActivity {

    @BindView(R.id.seek_bar)
    BubbleSeekBar mSeekBar;

    @Override
    protected int setLayout() {
        return R.layout.activity_seek_bar_test;
    }

    @Override
    protected void initView() {
        mSeekBar.getConfigBuilder()
                .min(0.0f)
                .max(50)
                .progress(20)
//                .sectionCount(5)
                .trackColor(Color.parseColor("#5808B0"))           //未加载进度颜色
                .secondTrackColor(Color.parseColor("#CA4AEF"))           //已加载进度颜色
                .thumbColor(ContextCompat.getColor(this, R.color.black))            //当前进度点
//                .showSectionText()
//                .sectionTextColor(ContextCompat.getColor(this, R.color.red))            //底部数字颜色
//                .sectionTextSize(18)
//                .showThumbText()
                .thumbTextColor(ContextCompat.getColor(this, R.color.green))            //当前进度数字颜色
//                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(this, R.color.purple))              //顶部水滴颜色
//                .bubbleTextSize(18)
//                .showSectionMark()
                .seekBySection()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
    }

    @Override
    protected void initData() {

    }




}
