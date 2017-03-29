package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 15/03/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public void init() {
        Button mBtnTestRecyclerView = (Button) findViewById(R.id.btnTestRecyclerView);
        Button mBtnTestIntent = (Button) findViewById(R.id.btnTestIntent);
        Button mBtnCheckIntent = (Button) findViewById(R.id.btnCheckIntent);
        Button mBtnSqlite = (Button) findViewById(R.id.btnSqlite);
        Button mBtnFragment = (Button) findViewById(R.id.btnFragment);
        Button mBtnViewPager = (Button) findViewById(R.id.btnViewPager);
        Button mBtnService = (Button) findViewById(R.id.btnService);
        Button mBtnBroadcast = (Button) findViewById(R.id.btnBroadcast);
        mBtnTestRecyclerView.setOnClickListener(this);
        mBtnTestIntent.setOnClickListener(this);
        mBtnCheckIntent.setOnClickListener(this);
        mBtnSqlite.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
        mBtnService.setOnClickListener(this);
        mBtnBroadcast.setOnClickListener(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTestRecyclerView:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
            case R.id.btnViewPager:
                intent = new Intent(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.btnService:
                intent = new Intent(this, MyServiceBindingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBroadcast:
                intent = new Intent(this, MyReceiverActivity.class);
                startActivity(intent);
                break;
        }
    }
}
