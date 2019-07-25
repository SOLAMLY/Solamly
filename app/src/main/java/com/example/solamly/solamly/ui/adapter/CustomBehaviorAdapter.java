package com.example.solamly.solamly.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.solamly.solamly.R;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/7/19 16:45
 * @Description:
 */

public class CustomBehaviorAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public CustomBehaviorAdapter(@Nullable List<String> data) {
        super(data);
        mLayoutResId = R.layout.item_custom_behavior;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
//        helper.setText(R.id.tv_item_custom_behavior_content,item);
//
//        helper.setOnClickListener(R.id.tv_item_custom_behavior_right, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                remove(helper.getAdapterPosition());
//            }
//        });
//
//        helper.setOnClickListener(R.id.tv_item_custom_behavior_left, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
