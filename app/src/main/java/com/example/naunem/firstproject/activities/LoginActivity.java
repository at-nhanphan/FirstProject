package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 08/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private Button mBtnLogin;
    private ImageView mImgShowPass;
    private TextView mTvCreateAccount;

    private void init() {
        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mImgShowPass = (ImageView) findViewById(R.id.imgShowPass);
        mTvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        mBtnLogin.setOnClickListener(this);
        mTvCreateAccount.setOnClickListener(this);
        mImgShowPass.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Toast.makeText(this, "Username: " + mEdtUsername.getText().toString() + "\nPassword: " +
                        mEdtPassword.getText().toString(), Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent(LoginActivity.this, ListUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", mEdtUsername.getText().toString());
                bundle.putString("password", mEdtPassword.getText().toString());
                startActivity(intentMain);
                break;
            case R.id.tvCreateAccount:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
        }
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
