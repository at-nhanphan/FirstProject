package com.example.naunem.firstproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.activities.DetailUserActivity;
import com.example.naunem.firstproject.adapters.UserAdapter;
import com.example.naunem.firstproject.interfaces.MyOnClickListener;
import com.example.naunem.firstproject.models.ItemList;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by naunem on 22/03/2017.
 */
public class ListUserFragment extends Fragment implements MyOnClickListener {
    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private ArrayList<User> mUsers;
    private TextView mTvTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDatabase mUserDatabase = new UserDatabase(getActivity());
        if (mUserDatabase.getAllUsers().size() == 0) {
            User user = new User("content://com.android.providers.media.documents/document/image%3A57", "nhan", "23", "male");
            mUserDatabase.insertUser(user);
        } else {
            mUsers = mUserDatabase.getAllUsers();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_user, container, false);
        mTvTitle = (TextView) view.findViewById(R.id.tvTitle);
        mTvTitle.setText("Fragment");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewListUser);
        LinearLayoutManager ln = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(ln);
        mUserAdapter = new UserAdapter(view.getContext(), mUsers, mRecyclerView, this);
        mRecyclerView.setAdapter(mUserAdapter);
        mUserAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onClickListener(int position) {
        User user = mUsers.get(position);
        Intent intent = new Intent(getActivity(), DetailUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        intent.putExtra("object", bundle);
        startActivity(intent);
    }
}

