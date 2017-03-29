package com.example.naunem.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 29/03/2017.
 */

public class ReceiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        TextView tvData = (TextView) findViewById(R.id.tvMessage);
        String data = getIntent().getStringExtra("content");
        tvData.setText(data);
    }
}
