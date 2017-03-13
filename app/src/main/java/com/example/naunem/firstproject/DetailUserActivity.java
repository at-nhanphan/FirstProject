package com.example.naunem.firstproject;

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

public class DetailUserActivity extends AppCompatActivity {
    TextView mTvName;
    TextView mTvAge;
    TextView mTvGender;
    ImageView mImgAvatar;
    ImageView mImgFavorite;
    User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        mTvName = (TextView) findViewById(R.id.tvName);
        mTvAge = (TextView) findViewById(R.id.tvAge);
        mTvGender = (TextView) findViewById(R.id.tvGender);
        mImgAvatar = (ImageView) findViewById(R.id.imgLogo);
        mImgFavorite = (ImageView) findViewById(R.id.imgFavorite);
        mImgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setFavorite(!mUser.isFavorite());
            }
        });

        mUser = getIntent().getBundleExtra("object").getParcelable("data");

        Log.d("toi muon biet", "onCreate: " + mUser.isFavorite());
        mImgAvatar.setImageResource(mUser.getImage());
        mTvName.setText(mUser.getName());
        mTvAge.setText(mUser.getAge());
        mTvGender.setText(mUser.getGender());
        mImgFavorite.setSelected(mUser.isFavorite());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
