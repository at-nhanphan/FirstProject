package com.example.naunem.firstproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.naunem.firstproject.fragments.ViewPagerFragment;

/**
 * Created by naunem on 22/03/2017.
 */

public class SlideViewPagerAdapter extends FragmentStatePagerAdapter {
    public SlideViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewPagerFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
