package com.example.naunem.firstproject.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.ViewById;

/**
 * PhoneCallActivity class
 * Created by naunem on 15/03/2017.
 */

@EActivity(R.layout.activity_phone_call)
public class PhoneCallActivity extends AppCompatActivity {
    private String mText = "";
    @ViewById(R.id.edtShow)
    EditText mEdtShow;

    @Click(R.id.tvZero)
    void clickZero() {
        mText += 0;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvOne)
    void clickOne() {
        mText += 1;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvTwo)
    void clickTwo() {
        mText += 2;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvThree)
    void clickThree() {
        mText += 3;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvFour)
    void clickFour() {
        mText += 4;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvFive)
    void clickFive() {
        mText += 5;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvSix)
    void clickSix() {
        mText += 6;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvSeven)
    void clickSeven() {
        mText += 7;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvEight)
    void clickEight() {
        mText += 8;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvNine)
    void clickNine() {
        mText += 9;
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvStar)
    void clickStar() {
        mText += "*";
        mEdtShow.setText(mText);
    }

    @Click(R.id.tvFly)
    void clickFly() {
        mText += "#";
        mEdtShow.setText(mText);
    }

    @Click(R.id.imgCall)
    void clickcallPhone() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + mText));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this, permissions, 10);
            return;
        }
        startActivity(callIntent);
    }

    @Click(R.id.imgBackspace)
    void clickBackspace() {
        mText.trim();
        if (mText.length() == mEdtShow.getSelectionStart()) {
            mText = mText.substring(0, mText.length() - 1);
        } else {
            mText = mText.substring(0, mEdtShow.getSelectionStart() - 1) + mText.substring(mEdtShow.getSelectionStart(), mText.length());
        }
        mEdtShow.setText(mText);
    }

    @LongClick(R.id.imgBackspace)
    void onLongClickBackspace() {
        mText = "";
        mEdtShow.setText(mText);
    }
}
