package com.example.solamly.solamly.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.solamly.solamly.Base.BaseFragment;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.ui.adapter.CustomBehaviorAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author SOLAMLY
 * @Date 2018/8/1 10:20
 * @Description:
 */

public class BFragment extends BaseFragment {
    CustomBehaviorAdapter adapter;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    List<String> data = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_b;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vie = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,vie);
        return vie;
    }

    @Override
    protected void initView() {
        adapter = new CustomBehaviorAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        for (int i = 0; i < 20; i++) {
            data.add(" 卡" + i);
        }
        adapter.setNewData(data
        );
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
