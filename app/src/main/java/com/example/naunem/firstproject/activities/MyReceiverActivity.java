package com.example.naunem.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 28/03/2017.
 */

public class MyReceiverActivity extends AppCompatActivity {

    private TextView mTvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);
        mTvContent = (TextView) findViewById(R.id.tvContent);
        String key = getIntent().getStringExtra("data");
        mTvContent.setText("Result: \n" + key);
    }
}
