package com.example.naunem.firstproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.naunem.firstproject.fragments.UserFragment;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;

/**
 * Created by naunem on 23/03/2017.
 */

public class UserViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<User> mUsers = new ArrayList<>();
    public UserViewPagerAdapter(FragmentManager fm, ArrayList<User> users) {
        super(fm);
        this.mUsers = users;
    }

    @Override
    public Fragment getItem(int position) {
        return new UserFragment();
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }
}
