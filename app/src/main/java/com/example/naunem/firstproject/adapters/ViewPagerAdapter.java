package com.example.naunem.firstproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.naunem.firstproject.fragments.ListUserFragment_;
import com.example.naunem.firstproject.fragments.PageAFragment_;

/**
 * ViewPagerAdapter class
 * Created by naunem on 22/03/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PageAFragment_();
            case 1:
                return new ListUserFragment_();
            case 2:
                return new ListUserFragment_();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
