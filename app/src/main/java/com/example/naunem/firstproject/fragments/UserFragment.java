package com.example.naunem.firstproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.activities.DetailUserActivity;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 *
 * Created by naunem on 23/03/2017.
 */

public class UserFragment extends Fragment {

    private ImageView mImgAvatar;
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvGender;
    private ImageView mImgFavorite;
    private ArrayList<User> mUsers;
    private UserDatabase mUserDatabase;
    private User user;
    private final int REQUEST_CODE = 4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_user, container, false);

        mImgAvatar = (ImageView) view.findViewById(R.id.imgLogo);
        mTvName = (TextView) view.findViewById(R.id.tvName);
        mTvAge = (TextView) view.findViewById(R.id.tvAge);
        mTvGender = (TextView) view.findViewById(R.id.tvGender);
        mImgFavorite = (ImageView) view.findViewById(R.id.imgFavorite);
        mUserDatabase = new UserDatabase(getContext());

        final int position = getArguments().getInt("position");
        mUsers = mUserDatabase.getAllUsers();
        user = mUsers.get(position);

        if (!TextUtils.isEmpty(user.getImage())) {
            Picasso.with(view.getContext())
                    .load(user.getImage())
                    .fit()
                    .centerCrop()
                    .into(mImgAvatar);
        } else {
            mImgAvatar.setImageResource(R.drawable.ic_girl);
        }
        mTvName.setText(user.getName());
        mTvAge.setText(user.getAge());
        mTvGender.setText(user.getGender());
        mImgFavorite.setSelected(user.isFavorite());
        mImgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgFavorite.setSelected(!user.isFavorite());
                user.setFavorite(!user.isFavorite());
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", user);
                intent.putExtra("index", position);
                intent.putExtra("object", bundle);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        return view;
    }

    public UserFragment newInstance(int position) {
        UserFragment fragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            boolean isCheck = data.getBooleanExtra("isCheck", false);
            int index = data.getIntExtra("index", -1);
            if (index != -1) {
                user = mUsers.get(index);
                user.setFavorite(isCheck);
                mImgFavorite.setSelected(user.isFavorite());
            }
        }
    }
}
