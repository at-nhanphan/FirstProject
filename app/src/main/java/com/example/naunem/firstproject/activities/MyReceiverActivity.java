package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 28/03/2017.
 */

public class MyReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);
        TextView mTvContent = (TextView) findViewById(R.id.tvContent);
        Button btnSendToOtherApp = (Button) findViewById(R.id.btnSendToOtherApp);
        Button btnSendToActivity = (Button) findViewById(R.id.btnSendToActivity);
        btnSendToOtherApp.setOnClickListener(this);
        btnSendToActivity.setOnClickListener(this);
        String key = getIntent().getStringExtra("flag");
        String time = getIntent().getStringExtra("time");
        mTvContent.setText("Result: \n message: " + key + "\n" + "time receive: " + time);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendToOtherApp:
                Intent intent = new Intent();
                String stringIntent = "com.example.naunem.intent.CUSTOM_INTENT";
                intent.setAction(stringIntent);
                intent.putExtra("data", stringIntent);
                sendBroadcast(intent);
                break;
            case R.id.btnSendToActivity:
                Intent i = new Intent();
                String receiveIntent = "com.pro.naunem.action.RECEIVE_INTENT";
                i.setAction(receiveIntent);
                i.putExtra("message", receiveIntent);
                sendBroadcast(i);
                break;
        }
    }
}
