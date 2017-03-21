package com.example.naunem.firstproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private static final String USER_NAME = "naunem";
    private static final String PASS_WORD = "123456";

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

        boolean check = checkLogin();
        if (check) {
            Intent itListUser = new Intent(this, ListUserActivity.class);
            startActivity(itListUser);
            finish();
        } else {
            init();
            mBtnLogin.setOnClickListener(this);
            mTvCreateAccount.setOnClickListener(this);
            mImgShowPass.setOnTouchListener(this);
        }
    }

    private boolean checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("Share", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (username.equalsIgnoreCase(USER_NAME) && password.equalsIgnoreCase(PASS_WORD)) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (mEdtUsername.length() == 0 || mEdtPassword.length() == 0) {
                    Toast.makeText(this, "enter username and pass", Toast.LENGTH_SHORT).show();
                } else if (!mEdtUsername.getText().toString().equalsIgnoreCase(USER_NAME) || !mEdtPassword.getText().toString().equalsIgnoreCase(PASS_WORD)) {
                    Toast.makeText(this, "username or password invalid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Username: " + mEdtUsername.getText().toString() + "\nPassword: " +
                            mEdtPassword.getText().toString(), Toast.LENGTH_LONG).show();
                    Intent intentMain = new Intent(LoginActivity.this, AddActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username", mEdtUsername.getText().toString());
//                    bundle.putString("password", mEdtPassword.getText().toString());
                    SharedPreferences share = getSharedPreferences("Share", MODE_PRIVATE);
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("username", mEdtUsername.getText().toString());
                    editor.putString("password", mEdtPassword.getText().toString());
                    editor.commit();
                    intentMain.putExtra("isFrist", true);
                    startActivity(intentMain);
                    finish();
                }
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
