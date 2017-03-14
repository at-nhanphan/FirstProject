package com.example.naunem.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by naunem on 10/03/2017.
 */

public class DetailUserActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTvName;
    TextView mTvAge;
    TextView mTvGender;
    ImageView mImgAvatar;
    ImageView mImgFavorite;
    User mUser;
    boolean mIsCheck;
    private int mIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        mTvName = (TextView) findViewById(R.id.tvName);
        mTvAge = (TextView) findViewById(R.id.tvAge);
        mTvGender = (TextView) findViewById(R.id.tvGender);
        mImgAvatar = (ImageView) findViewById(R.id.imgLogo);
        mImgFavorite = (ImageView) findViewById(R.id.imgFavorite);
        mImgFavorite.setOnClickListener(this);

        mUser = getIntent().getBundleExtra("object").getParcelable("data");
        mIndex = getIntent().getIntExtra("index", -1);
        Log.d("dfdjfajf", "onCreate: " + mIndex);

        Log.d("toi muon biet", "onCreate: " + mUser.isFavorite());
        mIsCheck = mUser.isFavorite();
        mImgAvatar.setImageResource(mUser.getImage());
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
