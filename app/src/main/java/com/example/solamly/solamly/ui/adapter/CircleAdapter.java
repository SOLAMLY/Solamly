package com.example.solamly.solamly.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.solamly.basemodule.util.other.MeasurementUtil;
import com.example.solamly.solamly.R;
import com.ruffian.library.widget.RImageView;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/10/15 10:05
 * @Description:
 */

public class CircleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CircleAdapter(@Nullable List<String> data) {
        super(data);
        mLayoutResId = R.layout.mall_item_main_classify;
    }

    private boolean isDataMre = false;

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        final RImageView mImageView = (RImageView) helper.getView(R.id.iv_type_icon);
//            final LinearLayout mLlClassify = (LinearLayout) helper.getView(R.id.ll_classify);
            int screenWidth = MeasurementUtil.getScreenWidth(mContext);
            LinearLayout.LayoutParams mParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
            int width = (screenWidth - MeasurementUtil.dip2px(mContext, 216)) / 5;
            mParams.setMargins(width / 2, MeasurementUtil.dip2px(mContext,10), width / 2, 0);
            helper.setText(R.id.tv_name,item+"sdfasdf");
    }
}
