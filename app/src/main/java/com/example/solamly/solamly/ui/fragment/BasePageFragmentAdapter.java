package com.example.solamly.solamly.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @Author SOLAMLY
 * @Date 2018/7/31 11:09
 * @Description:
 */

public class BasePageFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<Fragment> fragmentList;

    public BasePageFragmentAdapter(FragmentManager fm, String[] mTitles, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.mTitles = mTitles;
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
