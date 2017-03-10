package com.example.naunem.firstproject;

import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImgBack;
    ImageView mImgSettings;
    RecyclerView mRecyclerViewListUser;
    ListUserAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mImgSettings = (ImageView) findViewById(R.id.imgSettings);
        mImgBack.setOnClickListener(this);
        mImgSettings.setOnClickListener(this);
        mRecyclerViewListUser = (RecyclerView) findViewById(R.id.recyclerViewListUser);

        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewListUser.setLayoutManager(ln);

        ArrayList<User> datas = DataUser.getDataUser();

        mAdapter = new ListUserAdapter(this, datas);
        mRecyclerViewListUser.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgSettings:
                // TODO: 09/03/2017 make settings method
                break;
        }
    }
}
