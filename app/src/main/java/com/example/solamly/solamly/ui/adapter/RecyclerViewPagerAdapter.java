package com.example.solamly.solamly.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RecyclerViewPagerAdapter extends PagerAdapter {

    private final Random random = new Random();
    private int mSize;
    List<String> data = new ArrayList<>();

    private Context mContext;

    public RecyclerViewPagerAdapter( List<String> data) {
        this.data = data;
        mSize = 2;
    }

    public RecyclerViewPagerAdapter(int count) {
        mSize = count;
    }

    @Override
    public int getCount() {
        return mSize;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup view, int position, @NonNull Object object) {
        view.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        List<String> mData = data.subList(position * 0, position * 0 + 10);
        RecyclerView textView = new RecyclerView(view.getContext());
        CircleAdapter mCircleAdapter = new CircleAdapter(mData);
        textView.setLayoutManager(new GridLayoutManager(view.getContext(), 5, GridLayout.VERTICAL, false));
        textView.setAdapter(mCircleAdapter);

        view.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return textView;
    }

}