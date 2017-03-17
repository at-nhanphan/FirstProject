package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.adapters.UserAdapter;
import com.example.naunem.firstproject.interfaces.MyOnClickListener;
import com.example.naunem.firstproject.interfaces.OnLoadMoreListener;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.ItemList;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity implements View.OnClickListener, MyOnClickListener {

    private ImageView mImgBack;
    private ImageView mImgSettings;
    private RecyclerView mRecyclerViewListUser;
    private UserAdapter mAdapter;
    private Handler mHandler = new Handler();
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemList> mItemLists;
    private final int REQUEST_CODE = 1;

    private void init() {
        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mImgSettings = (ImageView) findViewById(R.id.imgSettings);
        mRecyclerViewListUser = (RecyclerView) findViewById(R.id.recyclerViewListUser);
        mImgBack.setOnClickListener(this);
        mImgSettings.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        init();
        mRecyclerViewListUser.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListUser.setLayoutManager(mLayoutManager);
        mItemLists = MockData.getData();
        mAdapter = new UserAdapter(this, mItemLists, mRecyclerViewListUser, this);

        mRecyclerViewListUser.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                // Add null , so the adapter will check view_type and show progress bar at bottom
                mItemLists.add(null);
                mAdapter.notifyItemInserted(mItemLists.size() - 1);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Remove progress item
                        mItemLists.remove(mItemLists.size() - 1);
                        mAdapter.notifyItemRemoved(mItemLists.size());
                        // Add items
                        int start = mItemLists.size();
                        int end = start + 20;
                        for (int i = start; i < end; i++) {
                            ItemList item = MockData.getDataById(i);
                            mItemLists.add(item);
                            mAdapter.notifyItemInserted(mItemLists.size());
                        }
                        mAdapter.setLoaded();
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
                Intent intent = new Intent(ListUserActivity.this, FavoriteActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", (Parcelable) mItemLists.get(position));
        intent.putExtra("object", bundle);
        intent.putExtra("index", position);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean isCheck = data.getBooleanExtra("isCheck", false);
                int index = data.getIntExtra("index", -1);
                User user = (User) mItemLists.get(index);
                user.setFavorite(isCheck);
                Log.d("xem nao", "onActivityResult: " + index);
                if (index != -1) {
                    mItemLists.set(index, mItemLists.get(index));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
