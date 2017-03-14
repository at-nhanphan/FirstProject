package com.example.naunem.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyOnClickListener {

    ImageView mImgBack;
    ImageView mImgSettings;
    RecyclerView mRecyclerViewListUser;
    ListUserAdapter mAdapter;
    android.os.Handler mHandler = new android.os.Handler();
    LinearLayoutManager mLayoutManager;
    ArrayList<User> mDatas;

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
        mDatas = DataUser.getDataUser(this);
        mAdapter = new ListUserAdapter(this, mDatas, mRecyclerViewListUser, this);

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
                            User user = DataUser.getUserById(i);
                            mDatas.add(user);
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

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", mDatas.get(position));
        intent.putExtra("object", bundle);
        intent.putExtra("index", position);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean isCheck = data.getBooleanExtra("isCheck", false);
                int index = data.getIntExtra("index", -1);
                mDatas.get(index).setFavorite(isCheck);
                Log.d("xem nao", "onActivityResult: " + index);
                if (index != -1) {
                    mDatas.set(index, mDatas.get(index));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
