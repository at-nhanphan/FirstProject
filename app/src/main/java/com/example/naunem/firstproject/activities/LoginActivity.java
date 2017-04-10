package com.example.naunem.firstproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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
    private static final String USER_NAME = "naunem";
    private static final String PASS_WORD = "123456";

    @AfterViews
    void init() {
        boolean check = checkLogin();
        if (check) {
            Intent itListUser = new Intent(this, ListUserActivity_.class);
            startActivity(itListUser);
            finish();
        }
    }

    private boolean checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("Share", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        return username.equalsIgnoreCase(USER_NAME) && password.equalsIgnoreCase(PASS_WORD);
    }

    @Click(R.id.btnLogin)
    void login() {
        if (mEdtUsername.length() == 0 || mEdtPassword.length() == 0) {
            Toast.makeText(this, "enter username and pass", Toast.LENGTH_SHORT).show();
        } else if (!mEdtUsername.getText().toString().equalsIgnoreCase(USER_NAME) || !mEdtPassword.getText().toString().equalsIgnoreCase(PASS_WORD)) {
            Toast.makeText(this, "username or password invalid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Username: " + mEdtUsername.getText().toString() + "\nPassword: " +
                    mEdtPassword.getText().toString(), Toast.LENGTH_LONG).show();
            Intent intentMain = new Intent(this, AddActivity_.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username", mEdtUsername.getText().toString());
//                    bundle.putString("password", mEdtPassword.getText().toString());
            SharedPreferences share = getSharedPreferences("Share", MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putString("username", mEdtUsername.getText().toString());
            editor.putString("password", mEdtPassword.getText().toString());
            editor.apply();
            intentMain.putExtra("isFrist", true);
            startActivity(intentMain);
            finish();
        }
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
