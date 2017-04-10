package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * RegisterActivity class
 * Created by naunem on 08/03/2017.
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    RadioButton mRbMale;
    RadioButton mRbFemale;
    @ViewById(R.id.chkFootball)
    CheckBox mChkFootball;
    @ViewById(R.id.chkListenMusic)
    CheckBox mChkListenMusic;
    @ViewById(R.id.chkSuftWeb)
    CheckBox mChkSuftWeb;
    @ViewById(R.id.chkCommic)
    CheckBox mChkCommic;
    Button mBtnRegister;
    ImageView mImgShowPass;
    ArrayList<CheckBox> mLists = new ArrayList<>();

    @AfterViews
    void init() {
        mLists.add(mChkFootball);
        mLists.add(mChkListenMusic);
        mLists.add(mChkSuftWeb);
        mLists.add(mChkCommic);
    }

    public String getGender(){
        String mGender = "";
        if (mRbMale.isChecked()) {
            mGender = mRbMale.getText().toString();
        } else {
            mGender = mRbFemale.getText().toString();
        }
        return "\nGender: " + mGender;
    }

    public String getHobby(){
        String mHobby = "";
        for (CheckBox item : mLists) {
            if (item.isChecked()) {
                mHobby += item.getText().toString() + " ";
            }
        }
        return "\nHobby: " + mHobby;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                String result = "";
                result += "Username: " + mEdtUsername.getText() + "\nPassword: " + mEdtPassword.getText() + getGender()  + getHobby();
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            mEdtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        if (MotionEvent.ACTION_UP == event.getAction()) {
            mEdtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        return true;
    }
}
