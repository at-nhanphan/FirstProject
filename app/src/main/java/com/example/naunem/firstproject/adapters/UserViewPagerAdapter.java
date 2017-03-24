package com.example.naunem.firstproject.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.naunem.firstproject.fragments.UserFragment;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import java.util.ArrayList;

/**
 * Created by naunem on 23/03/2017.
 */

public class UserViewPagerAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    private final UserDatabase mDatabase;
    private ArrayList<User> mUsers;

    public UserViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        mDatabase = new UserDatabase(context);
    }

    @Override
    public Fragment getItem(int position) {
        return new UserFragment().newInstance(position);
    }

    @Override
    public int getCount() {
        mUsers = mDatabase.getAllUsers();
        return mUsers.size();
    }
}
