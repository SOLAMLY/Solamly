package com.example.solamly.solamly.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.Util.MeasurementUtil;
import com.example.solamly.solamly.ui.adapter.CustomBehaviorAdapter;
import com.example.solamly.solamly.ui.fragment.BasePageFragmentAdapter;
import com.example.solamly.solamly.ui.fragment.Fragments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author SOLAMLY
 * @Date 2018/7/19 16:29
 * @Description:
 */

public class CustomBehaviorActivity extends BaseActivity {
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;

    @BindView(R.id.iv_custom_behavior_cover)
    ImageView imageView;
    @BindView(R.id.rv_custom_behavior)
    RecyclerView recyclerView;
    @BindView(R.id.bottom_custom_behavior)
    LinearLayout llBottom;

    @BindView(R.id.toolbar_custom_behavior)
    Toolbar toolbar;

    private CustomBehaviorAdapter adapter;
    private List<String> list = new ArrayList<>();

    private BottomSheetBehavior bottomSheetBehavior;

    private BasePageFragmentAdapter adapters;
    @BindView(R.id.tl_info_personal_home_page)
    TabLayout tabLayout;
    @BindView(R.id.view)
    ViewPager viewPager;

    @Override
    protected int setLayout() {
        return R.layout.activity_custom_behavior;
    }

    @Override
    protected void initView() {
        /**
         * ToolBar
         */
        setSupportActionBar(toolbar);
        //添加返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //添加toolBar的距离顶部的距离
        int statusBarHeight = MeasurementUtil.getStatusBarHeight(this);
        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.setMargins(0, statusBarHeight, 0, 0);


        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        adapter = new CustomBehaviorAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /**
         *  底部Bottom -- 默认显示
         */
        bottomSheetBehavior = BottomSheetBehavior.from(llBottom);
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragments());
        fragments.add(new Fragments());
        adapters = new BasePageFragmentAdapter(getSupportFragmentManager(),new String[]{"sd","sdf"},fragments);
        viewPager.setAdapter(adapters);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initData() {

        Glide.with(this)
                .load(R.mipmap.bg_image)
                .asBitmap()
                .centerCrop()
                .into(imageView);

        for (int i = 0; i < 50; i++) {
            list.add("我是第" + i + "个");
        }
        adapter.setNewData(list);
    }

    @Override
    protected void setListener() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * @param recyclerView
             * @param dx    水平滚动距离:dx > 0 时为向左滚动,dx < 0 时为向右滚动,
             * @param dy    垂直滚动距离:dy > 0 时为向上滚动,dy < 0 时为向下滚动,
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                } else if (dy > 0) {
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == android.R.id.home) {
                    finish();
                }
                return false;
            }
        });
    }

    @OnClick({
    })
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
