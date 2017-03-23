package com.example.naunem.firstproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;

/**
 * Created by naunem on 23/03/2017.
 */

public class UserFragment extends Fragment {
    private ImageView mImgAvatar;
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvGender;
    private ImageView mImgFavorite;
    private ArrayList<User> mUsers;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImgAvatar = (ImageView) getActivity().findViewById(R.id.imgLogo);
        mTvName = (TextView) getActivity().findViewById(R.id.tvName);
        mTvAge = (TextView) getActivity().findViewById(R.id.tvAge);
        mTvGender = (TextView) getActivity().findViewById(R.id.tvGender);
        mImgFavorite = (ImageView) getActivity().findViewById(R.id.imgFavorite);
        mUsers = MockData.getAllUsers();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_user, container, false);
//        User user = mUsers.get(1);
//        mTvName.setText(user.getName());
//        mTvAge.setText(user.getAge());
//        mTvGender.setText(user.getGender());
//        mImgFavorite.setSelected(true);
        return view;
    }
}
