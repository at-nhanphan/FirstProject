package com.example.naunem.firstproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.naunem.firstproject.fragments.ListUserFragment;
import com.example.naunem.firstproject.fragments.PageAFragment;

/**
 * Created by naunem on 22/03/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public ViewPagerAdapter(FragmentManager fm, int mTabCount) {
        super(fm);
        this.mTabCount = mTabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PageAFragment();
            case 1:
                return new ListUserFragment();
            case 2:
                return new ListUserFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
