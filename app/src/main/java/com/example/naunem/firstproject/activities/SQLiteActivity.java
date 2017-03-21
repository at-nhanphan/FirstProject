package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.SqliteUser;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

/**
 * Created by naunem on 17/03/2017.
 */

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtName;
    private EditText mEdtAge;
    private EditText mEdtGender;
    private Button mBtnInsert;
    private Button mBtnShowInfo;
    private UserDatabase db = new UserDatabase(this);

    private void init() {
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mEdtGender = (EditText) findViewById(R.id.edtGender);
        mBtnInsert = (Button) findViewById(R.id.btnInsert);
        mBtnShowInfo = (Button) findViewById(R.id.btnShowInfo);
        mBtnInsert.setOnClickListener(this);
        mBtnShowInfo.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                User user = new User();
                db.insertUser(user);
                mEdtGender.setText("size " + db.getAllUsers().size());
                break;
            case R.id.btnShowInfo:
                Intent intent = new Intent(this, SQLiteShowListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
