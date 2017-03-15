package com.example.naunem.firstproject.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 15/03/2017.
 */

public class PhoneCallActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    TextView mTv1;
    TextView mTv2;
    TextView mTv3;
    TextView mTv4;
    TextView mTv5;
    TextView mTv6;
    TextView mTv7;
    TextView mTv8;
    TextView mTv9;
    TextView mTv0;
    TextView mTvStar;
    TextView mTvFly;
    ImageView mImgCall;
    EditText mEdtShow;
    ImageView mImgBackspace;
    private String mText = "";

    public void init() {
        mTv0 = (TextView) findViewById(R.id.tvZero);
        mTv1 = (TextView) findViewById(R.id.tvOne);
        mTv2 = (TextView) findViewById(R.id.tvTwo);
        mTv3 = (TextView) findViewById(R.id.tvThree);
        mTv4 = (TextView) findViewById(R.id.tvFour);
        mTv5 = (TextView) findViewById(R.id.tvFive);
        mTv6 = (TextView) findViewById(R.id.tvSix);
        mTv7 = (TextView) findViewById(R.id.tvSeven);
        mTv8 = (TextView) findViewById(R.id.tvEight);
        mTv9 = (TextView) findViewById(R.id.tvNine);
        mTvStar = (TextView) findViewById(R.id.tvStar);
        mTvFly = (TextView) findViewById(R.id.tvFly);
        mImgCall = (ImageView) findViewById(R.id.imgCall);
        mEdtShow = (EditText) findViewById(R.id.edtShow);
        mImgBackspace = (ImageView) findViewById(R.id.imgBackspace);

        mTv0.setOnClickListener(this);
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);
        mTv4.setOnClickListener(this);
        mTv5.setOnClickListener(this);
        mTv6.setOnClickListener(this);
        mTv7.setOnClickListener(this);
        mTv8.setOnClickListener(this);
        mTv9.setOnClickListener(this);
        mTvStar.setOnClickListener(this);
        mTvFly.setOnClickListener(this);
        mImgCall.setOnClickListener(this);
        mImgBackspace.setOnClickListener(this);
        mImgBackspace.setOnLongClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        init();
        mEdtShow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("ddddd", "onTextChanged: " + start + "---" + before + "---" + count);

            }

            @Override
            public void afterTextChanged(Editable s) {
                mEdtShow.setSelection(mText.length());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvZero:
                mText += 0;
                mEdtShow.setText(mText);
                break;
            case R.id.tvOne:
                mText += 1;
                mEdtShow.setText(mText);
                break;
            case R.id.tvTwo:
                mText += 2;
                mEdtShow.setText(mText);
                break;
            case R.id.tvThree:
                mText += 3;
                mEdtShow.setText(mText);
                break;
            case R.id.tvFour:
                mText += 4;
                mEdtShow.setText(mText);
                break;
            case R.id.tvFive:
                mText += 5;
                mEdtShow.setText(mText);
                break;
            case R.id.tvSix:
                mText += 6;
                mEdtShow.setText(mText);
                break;
            case R.id.tvSeven:
                mText += 7;
                mEdtShow.setText(mText);
                break;
            case R.id.tvEight:
                mText += 8;
                mEdtShow.setText(mText);
                break;
            case R.id.tvNine:
                mText += 9;
                mEdtShow.setText(mText);
                break;
            case R.id.tvStar:
                mText += "*";
                mEdtShow.setText(mText);
                break;
            case R.id.tvFly:
                mText += "#";
                mEdtShow.setText(mText);
                break;
            case R.id.imgBackspace:
                mText.trim();
                if (mText.length() == mEdtShow.getSelectionStart()) {
                    mText = mText.substring(0, mText.length() - 1);
                } else {
                    mText = mText.substring(0, mEdtShow.getSelectionStart() - 1) + mText.substring(mEdtShow.getSelectionStart(), mText.length());
                }
                mEdtShow.setText(mText);
                break;
            case R.id.imgCall:
                actionCall();
                Log.d("dsfsdf", "onClick: ");
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mText = "";
        mEdtShow.setText(mText);
        return true;
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Log.d("log", "requescode: " + requestCode);
//        if (requestCode == 10) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.d("log", "onRequestPermissionsResult: " + grantResults[0] + "---" + grantResults.length);
//                actionCall();
//            }
//        }
//    }

    private void actionCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + mText));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this, permissions, 10);
            return;
        }
        startActivity(callIntent);
    }
}
