package com.example.naunem.firstproject.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.UserViewPagerAdapter;

/**
 * Created by naunem on 23/03/2017.
 */

public class PageAFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_page1, container, false);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        UserViewPagerAdapter mAdapter = new UserViewPagerAdapter(getChildFragmentManager(), view.getContext());
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }
}
