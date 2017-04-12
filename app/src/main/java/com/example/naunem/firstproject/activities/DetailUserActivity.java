package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * DetailUserActivity class
 * Created by naunem on 10/03/2017.
 */

@EActivity(R.layout.activity_detail_user)
public class DetailUserActivity extends AppCompatActivity {
    @ViewById(R.id.tvName)
    TextView mTvName;
    @ViewById(R.id.tvAge)
    TextView mTvAge;
    @ViewById(R.id.tvGender)
    TextView mTvGender;
    @ViewById(R.id.imgFavorite)
    ImageView mImgFavorite;
    @ViewById(R.id.imgLogo)
    ImageView mImgAvatar;
    @Extra
    boolean mIsCheck;
    @Extra
    User mUser;
    @Extra
    int mIndex;
    
    @AfterViews
    void init() {
        mIsCheck = mUser.isFavorite();
        if (!TextUtils.isEmpty(mUser.getImage())) {
            Picasso.with(this)
                    .load(mUser.getImage())
                    .fit()
                    .into(mImgAvatar);
        } else {
            mImgAvatar.setImageResource(R.drawable.ic_boy);
        }
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

    @Click(R.id.imgFavorite)
    void clickFavorite() {
        if (mIsCheck) {
            mImgFavorite.setSelected(!mIsCheck);
            mIsCheck = false;
        } else {
            mImgFavorite.setSelected(!mIsCheck);
            mIsCheck = true;
        }
    }
}
