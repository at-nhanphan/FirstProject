package com.example.naunem.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.ViewPagerAdapter;

/**
 * Created by naunem on 22/03/2017.
 */

public class ViewPagerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

//    private ViewPager mViewPager;
//    private PagerAdapter mPagerAdapter;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_viewpager);
//
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(mPagerAdapter);
//    }
//    @Override
//    public void onBackPressed() {
//        if (mViewPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
//        }
//    }

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        final TabLayout.Tab mHome = mTabLayout.newTab();
        final TabLayout.Tab mFavorite = mTabLayout.newTab();
        final TabLayout.Tab mSetting = mTabLayout.newTab();
        mHome.setIcon(R.drawable.ic_home_pink_400_24dp);
        mFavorite.setIcon(R.drawable.ic_favorite_white_24dp);
        mSetting.setIcon(R.drawable.ic_settings_white_24dp);
        mHome.setText("Home");
        mFavorite.setText("Favorite");
        mSetting.setText("Setting");
        mTabLayout.addTab(mHome, 0);
        mTabLayout.addTab(mFavorite, 1);
        mTabLayout.addTab(mSetting, 2);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.colorBlue));
        mTabLayout.setOnTabSelectedListener(this);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mHome.setIcon(R.drawable.ic_home_pink_400_24dp);
                        mFavorite.setIcon(R.drawable.ic_favorite_white_24dp);
                        mSetting.setIcon(R.drawable.ic_settings_white_24dp);
                        break;
                    case 1:
                        mHome.setIcon(R.drawable.ic_home_white_24dp);
                        mFavorite.setIcon(R.drawable.ic_favorite_pink_400_24dp);
                        mSetting.setIcon(R.drawable.ic_settings_white_24dp);
                        break;
                    case 2:
                        mHome.setIcon(R.drawable.ic_home_white_24dp);
                        mFavorite.setIcon(R.drawable.ic_favorite_white_24dp);
                        mSetting.setIcon(R.drawable.ic_settings_pink_400_24dp);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
