package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.fragments.ListUserFragment;

/**
 * Created by naunem on 15/03/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnTestRecyclerView;
    private Button mBtnTestIntent;
    private Button mBtnCheckIntent;
    private Button mBtnSqlite;
    private Button mBtnFragment;
    private Intent intent;

    public void init() {
        mBtnTestRecyclerView = (Button) findViewById(R.id.btnTestRecyclerView);
        mBtnTestIntent = (Button) findViewById(R.id.btnTestIntent);
        mBtnCheckIntent = (Button) findViewById(R.id.btnCheckIntent);
        mBtnSqlite = (Button) findViewById(R.id.btnSqlite);
        mBtnFragment = (Button) findViewById(R.id.btnFragment);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mBtnTestRecyclerView.setOnClickListener(this);
        mBtnTestIntent.setOnClickListener(this);
        mBtnCheckIntent.setOnClickListener(this);
        mBtnSqlite.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTestRecyclerView:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTestIntent:
                intent = new Intent(MainActivity.this, PhoneCallActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCheckIntent:
                intent = new Intent(MainActivity.this, IntentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSqlite:
                intent = new Intent(this, SQLiteActivity.class);
                startActivity(intent);
                break;
            case R.id.btnFragment:
                intent = new Intent(this, FragmentDemoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
