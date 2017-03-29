package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.services.MyService;

/**
 * Created by naunem on 27/03/2017.
 */

public class MyServiceBindingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStart;
    private Button mBtnStop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mBtnStart = (Button) findViewById(R.id.btnStart);
        mBtnStop = (Button) findViewById(R.id.btnStop);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MyServiceBindingActivity.this, MyService.class);
        switch (v.getId()) {
            case R.id.btnStart:
                startService(intent);
                break;
            case R.id.btnStop:
                stopService(intent);
                break;
        }
    }
}
