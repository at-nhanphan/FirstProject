package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.squareup.picasso.Picasso;

/**
 * Created by naunem on 10/03/2017.
 */

public class DetailUserActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvGender;
    private ImageView mImgAvatar;
    private ImageView mImgFavorite;
    private boolean mIsCheck;
    private int mIndex;

    private void init() {
        mTvName = (TextView) findViewById(R.id.tvName);
        mTvAge = (TextView) findViewById(R.id.tvAge);
        mTvGender = (TextView) findViewById(R.id.tvGender);
        mImgAvatar = (ImageView) findViewById(R.id.imgLogo);
        mImgFavorite = (ImageView) findViewById(R.id.imgFavorite);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        init();
        mImgFavorite.setOnClickListener(this);

        User mUser = getIntent().getBundleExtra("object").getParcelable("user");
        mIndex = getIntent().getIntExtra("index", -1);
        mIsCheck = mUser.isFavorite();
        Picasso.with(this)
                .load(mUser.getImage())
                .fit()
                .centerCrop()
                .into(mImgAvatar);
        mTvName.setText(mUser.getName());
        mTvAge.setText(mUser.getAge());
        mTvGender.setText(mUser.getGender());
        mImgFavorite.setSelected(mUser.isFavorite());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isCheck", mIsCheck);
        if (mIndex != -1) {
            intent.putExtra("index", mIndex);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (mIsCheck) {
            mImgFavorite.setSelected(!mIsCheck);
            mIsCheck = false;
        } else {
            mImgFavorite.setSelected(!mIsCheck);
            mIsCheck = true;
        }
    }
}
