package com.example.naunem.firstproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.naunem.firstproject.fragments.PageAFragment;
import com.example.naunem.firstproject.fragments.PageBFragment;
import com.example.naunem.firstproject.fragments.PageCFragment;

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
                PageAFragment page1 = new PageAFragment();
                return page1;
            case 1:
                PageBFragment page2 = new PageBFragment();
                return page2;
            case 2:
                PageCFragment page3 = new PageCFragment();
                return page3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
