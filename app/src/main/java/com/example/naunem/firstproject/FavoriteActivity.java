package com.example.naunem.firstproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by naunem on 14/03/2017.
 */

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FavoriteAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }
}
