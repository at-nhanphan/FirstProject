package com.example.naunem.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.SqliteUserAdapter;
import com.example.naunem.firstproject.models.SqliteUser;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import java.util.ArrayList;

/**
 * Created by naunem on 20/03/2017.
 */

public class SQLiteShowListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SqliteUserAdapter mAdapter;
    private UserDatabase db = new UserDatabase(this);
    private ArrayList<User> users;

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewListUser);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        init();

        LinearLayoutManager ln =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(ln);
        users = db.getAllUsers();
        Log.d("afsfaf", "onCreate: " + users.size());
        mAdapter = new SqliteUserAdapter(this, users);
        mRecyclerView.setAdapter(mAdapter);
    }
}
