package com.example.solamly.basemodule.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by: SOLAMLY
 * Date:2018/11/19 0019
 */
public class FragmentControlManager {
    private AppCompatActivity mActivity;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private int mFrameLayoutId;


    public void init(AppCompatActivity mainActivity, int frameLayoutId) {
        mActivity = mainActivity;
        mFrameLayoutId = frameLayoutId;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    /**
     * 多个Fragment 切换
     * @param targetFragment
     */
    public final void showPage(Fragment targetFragment) {
        if (mCurrentFragment == targetFragment) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!targetFragment.isAdded()) {
            if (mCurrentFragment == null) {
                transaction.add(mFrameLayoutId, targetFragment);
            } else {
                transaction
                    .hide(mCurrentFragment)
                    .add(mFrameLayoutId, targetFragment);
            }
        } else {
            transaction
                .hide(mCurrentFragment)
                .show(targetFragment);

        }
        mCurrentFragment = targetFragment;
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commitAllowingStateLoss();
    }


    /**
     * 隐藏Fragment
     * @param targetFragment
     */
    public final void hidePage(Fragment targetFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(targetFragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 控制单个Fragment的显示
     */
    public final void showSingle(Fragment targetFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!targetFragment.isAdded()){
            transaction.add(mFrameLayoutId,targetFragment);
        }else{
            transaction.show(targetFragment);
        }
        transaction.commitAllowingStateLoss();
    }
}
