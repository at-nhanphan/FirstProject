package com.example.naunem.firstproject;

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

import java.util.ArrayList;

/**
 * Created by naunem on 08/03/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    EditText mEdtUsername;
    EditText mEdtPassword;
    RadioButton mRbMale;
    RadioButton mRbFemale;
    CheckBox mChkFootball;
    CheckBox mChkListenMusic;
    CheckBox mChkSuftWeb;
    CheckBox mChkCommic;
    Button mBtnRegister;
    ImageView mImgShowPass;
    ArrayList<CheckBox> mLists = new ArrayList<>();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mRbMale = (RadioButton) findViewById(R.id.rbMale);
        mRbFemale = (RadioButton) findViewById(R.id.rbFemale);
        mChkFootball = (CheckBox) findViewById(R.id.chkFootball);
        mChkListenMusic = (CheckBox) findViewById(R.id.chkListenMusic);
        mChkSuftWeb = (CheckBox) findViewById(R.id.chkSuftWeb);
        mChkCommic = (CheckBox) findViewById(R.id.chkCommic);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mBtnRegister.setOnClickListener(this);
        mImgShowPass = (ImageView) findViewById(R.id.imgShowPass);
        mImgShowPass.setOnTouchListener(this);

        mLists.add(mChkFootball);
        mLists.add(mChkListenMusic);
        mLists.add(mChkSuftWeb);
        mLists.add(mChkCommic);
    }

    /** get Gender of user
     *
     * @return String mGender
     */
    public String getGender(){
        String mGender = "";
        if (mRbMale.isChecked()) {
            mGender = mRbMale.getText().toString();
        } else {
            mGender = mRbFemale.getText().toString();
        }
        return "\nGender: " + mGender;
    }

    /** get Hobby of user
     *
     * @return String mHobby
     */
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
