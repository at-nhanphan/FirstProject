package com.example.naunem.firstproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 *
 * Created by naunem on 23/03/2017.
 */

@EFragment(R.layout.item_list_user)
public class UserFragment extends Fragment {

    @ViewById(R.id.imgLogo)
    ImageView mImgAvatar;
    @ViewById(R.id.tvName)
    TextView mTvName;
    @ViewById(R.id.tvAge)
    TextView mTvAge;
    @ViewById(R.id.tvGender)
    TextView mTvGender;
    @ViewById(R.id.imgFavorite)
    ImageView mImgFavorite;
    private ArrayList<User> mUsers;
    private UserDatabase mUserDatabase;
    private User user;
    private final int REQUEST_CODE = 4;

    @AfterViews
    void init() {
        mUserDatabase = new UserDatabase(getContext());
        final int position = getArguments().getInt("position");
        mUsers = mUserDatabase.getAllUsers();
        user = mUsers.get(position);

        if (!TextUtils.isEmpty(user.getImage())) {
            Picasso.with(getContext())
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
    }

    public UserFragment_ newInstance(int position) {
        UserFragment_ fragment = new UserFragment_();
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
