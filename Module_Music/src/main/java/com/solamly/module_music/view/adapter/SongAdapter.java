package com.solamly.module_music.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/10/26 16:30
 * @Description:
 */

public class SongAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SongAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
