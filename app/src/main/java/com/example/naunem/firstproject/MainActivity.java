package com.example.naunem.firstproject;

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
    android.os.Handler mHandler = new android.os.Handler();
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mImgSettings = (ImageView) findViewById(R.id.imgSettings);
        mImgBack.setOnClickListener(this);
        mImgSettings.setOnClickListener(this);
        mRecyclerViewListUser = (RecyclerView) findViewById(R.id.recyclerViewListUser);

        mRecyclerViewListUser.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListUser.setLayoutManager(mLayoutManager);
        final ArrayList<User> mDatas = DataUser.getDataUser(this);
        mAdapter = new ListUserAdapter(this, mDatas, mRecyclerViewListUser);
        mRecyclerViewListUser.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
                mDatas.add(null);
                mAdapter.notifyItemInserted(mDatas.size() - 1);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
                        mDatas.remove(mDatas.size() - 1);
                        mAdapter.notifyItemRemoved(mDatas.size());
                        //add items one by one
                        int start = mDatas.size();
                        int end = start + 20;
                        for (int i = start; i < end; i++) {
                            mDatas.add(new User(R.drawable.ic_boy, "User " +i, "Age" +i, "Gender " +i, R.drawable.ic_unlike, false));
                            mAdapter.notifyItemInserted(mDatas.size());
                        }
                        mAdapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
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
