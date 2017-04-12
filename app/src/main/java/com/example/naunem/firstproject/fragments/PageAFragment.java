package com.example.naunem.firstproject.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.UserViewPagerAdapter;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 *
 * Created by naunem on 23/03/2017.
 */

@EFragment(R.layout.viewpager_page1)
public class PageAFragment extends Fragment {

    private ArrayList<User> mUsers;
    private UserDatabase mDatabase;

    @ViewById(R.id.viewPager)
    ViewPager mViewPager;
    @AfterViews
    void init() {
        mDatabase = new UserDatabase(getContext());
        mUsers = mDatabase.getAllUsers();
        mViewPager.setPageMargin(20);
        UserViewPagerAdapter mAdapter = new UserViewPagerAdapter(getChildFragmentManager(), getContext(), mUsers);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mUsers.size());
        mAdapter.notifyDataSetChanged();
    }
}