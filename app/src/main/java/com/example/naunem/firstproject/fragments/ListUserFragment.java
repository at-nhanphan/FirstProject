package com.example.naunem.firstproject.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.activities.DetailUserActivity_;
import com.example.naunem.firstproject.adapters.UserAdapter;
import com.example.naunem.firstproject.interfaces.MyOnClickListener;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * ListUserFragment class
 * Created by naunem on 22/03/2017.
 */

@EFragment(R.layout.activity_list_user)
public class ListUserFragment extends Fragment implements MyOnClickListener {
    private UserAdapter mUserAdapter;
    private ArrayList<User> mUsers;
    private final int REQUEST_CODE = 7;

    @ViewById(R.id.tvTitle)
    TextView mTvTitle;
    @ViewById(R.id.recyclerViewListUser)
    RecyclerView mRecyclerView;

    @AfterViews
    void init() {
        UserDatabase mUserDatabase = new UserDatabase(getActivity());
        if (mUserDatabase.getAllUsers().size() == 0) {
            User user = new User("content://com.android.providers.media.documents/document/image%3A57", "nhan", "23", "male");
            mUserDatabase.insertUser(user);
        } else {
            mUsers = mUserDatabase.getAllUsers();
        }

        mTvTitle.setText(R.string.button_text_name1);
        LinearLayoutManager ln = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(ln);
        mUserAdapter = new UserAdapter(getContext(), mUsers, mRecyclerView, this);
        mRecyclerView.setAdapter(mUserAdapter);
        mUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickListener(int position) {
        DetailUserActivity_.intent(this).mIndex(position).mUser(mUsers.get(position)).startForResult(REQUEST_CODE);
    }

    @OnActivityResult(REQUEST_CODE)
    void onResult(int resultCode, Intent data, @OnActivityResult.Extra("isCheck") boolean isCheck,
                  @OnActivityResult.Extra("index") int index) {
        if (resultCode == RESULT_OK && null != data) {
            if (index != -1) {
                User user = mUsers.get(index);
                user.setFavorite(isCheck);
                mUsers.set(index, mUsers.get(index));
                mUserAdapter.notifyDataSetChanged();
            }
        }
    }
}

