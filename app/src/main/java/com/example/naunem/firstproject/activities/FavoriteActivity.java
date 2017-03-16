package com.example.naunem.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.naunem.firstproject.adapters.FavoriteAdapter;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;

/**
 * Created by naunem on 14/03/2017.
 */

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FavoriteAdapter mAdapter;
    ArrayList<User> mListUsers;
    User mUser;

    public void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewFavorite);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        init();
        LinearLayoutManager ln = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(ln);

        mListUsers = new ArrayList<>();
        mUser = getIntent().getBundleExtra("data").getParcelable("favorite");
        Log.d("favorite", "onCreate: " + mUser.getName());
        mListUsers.add(mUser);
        mAdapter = new FavoriteAdapter(this, mListUsers, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }
}
