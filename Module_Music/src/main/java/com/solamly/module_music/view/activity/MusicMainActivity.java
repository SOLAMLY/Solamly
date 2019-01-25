package com.solamly.module_music.view.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.solamly.basemodule.base.ui.BaseDataBindingActivity;
import com.example.solamly.musicmodule.R;
import com.example.solamly.musicmodule.R2;

import butterknife.BindView;

/**
 * @Author SOLAMLY
 * @Date 2018/10/26 15:57
 * @Description:
 */


public class MusicMainActivity extends BaseDataBindingActivity {
    @BindView(R2.id.recycleView)
    RecyclerView mRecycleView;

    @BindView(R2.id.status_bar)
    View mStatusBar;

    @Override
    protected int setContentLayout() {
        return R.layout.music_activity_main;
    }

    @Override
    protected void initView() {

        initStatusBar(mStatusBar);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    Handler handler = new Handler();

    @Override
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            skipARouter("/activity/CustomBehaviorActivity", null);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected void setListeners() {

    }


}
