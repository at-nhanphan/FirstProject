package com.example.naunem.firstproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        mTvName = (TextView) findViewById(R.id.tvName);
        mTvAge = (TextView) findViewById(R.id.tvAge);
        mTvGender = (TextView) findViewById(R.id.tvGender);
        mImgAvatar = (ImageView) findViewById(R.id.imgLogo);

        User user = getIntent().getBundleExtra("object").getParcelable("data");
        Log.d("TAG", "onCreate: " + user.getName());
        mImgAvatar.setImageResource(user.getImage());
        mTvName.setText(user.getName());
        mTvAge.setText(user.getAge());
        mTvGender.setText(user.getGender());
    }
}
