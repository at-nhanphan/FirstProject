package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.naunem.firstproject.R;

/**
 * This is MainActivity
 * Created by naunem on 15/03/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public void init() {
        Button btnTestRecyclerView = (Button) findViewById(R.id.btnTestRecyclerView);
        Button btnTestIntent = (Button) findViewById(R.id.btnTestIntent);
        Button btnCheckIntent = (Button) findViewById(R.id.btnCheckIntent);
        Button btnSqlite = (Button) findViewById(R.id.btnSqlite);
        Button btnFragment = (Button) findViewById(R.id.btnFragment);
        Button btnViewPager = (Button) findViewById(R.id.btnViewPager);
        Button btnService = (Button) findViewById(R.id.btnService);
        Button btnBroadcast = (Button) findViewById(R.id.btnBroadcast);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        btnTestRecyclerView.setOnClickListener(this);
        btnTestIntent.setOnClickListener(this);
        btnCheckIntent.setOnClickListener(this);
        btnSqlite.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
        btnViewPager.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnBroadcast.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnAsyncTask.setOnClickListener(this);
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
            case R.id.btnMap:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAsyncTask:
                intent = new Intent(this, AsyncTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
