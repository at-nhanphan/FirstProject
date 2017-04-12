package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.interfaces.SharePref_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * LoginActivity
 * Created by naunem on 08/03/2017.
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements View.OnTouchListener {

    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.btnLogin)
    Button mBtnLogin;
    @ViewById(R.id.imgShowPass)
    ImageView mImgShowPass;
    @ViewById(R.id.tvCreateAccount)
    TextView mTvCreateAccount;

    @Pref
    SharePref_ sharePref;

    @AfterViews
    void init() {
        if (sharePref.username().exists()) {
            ListUserActivity_.intent(this).start();
            finish();
        }
    }

    @Click(R.id.btnLogin)
    void login() {
        sharePref.username().put(mEdtUsername.getText().toString());
        sharePref.password().put(mEdtPassword.getText().toString());
        AddActivity_.intent(this).start();
        finish();
    }

    @Click(R.id.tvCreateAccount)
    void createAccount() {
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            Log.d("TAG", "onTouch: down");
            mEdtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        if (MotionEvent.ACTION_UP == event.getAction()) {
            mEdtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Log.d("TAG", "onTouch: up");
        }
        return true;
    }
}
