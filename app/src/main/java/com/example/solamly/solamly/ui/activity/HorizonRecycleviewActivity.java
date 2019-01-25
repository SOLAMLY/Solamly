package com.example.solamly.solamly.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.ui.adapter.CircleAdapter;
import com.example.solamly.solamly.ui.adapter.RecyclerViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * @Author SOLAMLY
 * @Date 2019/1/24 10:27
 * @Description:
 */
public class HorizonRecycleviewActivity extends BaseActivity {
    @BindView(R.id.rv_classify)
    RecyclerView mRvClassify;
    @BindView(R.id.progress_circular)
    CircleIndicator mCircleIndicator;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private CircleAdapter mCircleAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_horizon_recyclerview;
    }

    @Override
    protected void initView() {
                List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("i"+i);
        }
        mViewPager.setAdapter(new RecyclerViewPagerAdapter(data));
//        mViewPager.setAdapter(new SamplePagerAdapter(2));
//        mCircleIndicator.setViewPager(mViewPager);



//        mCircleAdapter = new CircleAdapter(data);
//        mRvClassify.setLayoutManager(new GridLayoutManager(this, 2, GridLayout.HORIZONTAL, false));
////        mRvClassify.setLayoutManager(new LinearLayoutManager(this,  GridLayout.HORIZONTAL, false));
//        mRvClassify.setAdapter(mCircleAdapter);

//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(mRvClassify);
////        mRvClassify.addItemDecoration(new LinePagerIndicatorDecoration());
//        mCircleIndicator.attachToRecyclerView(mRvClassify, snapHelper);
//
//
//        // CircleIndicator2 for RecyclerView
//        mCircleAdapter.registerAdapterDataObserver(mCircleIndicator.getAdapterDataObserver());

    }

    @Override
    protected void initData() {

    }


}
