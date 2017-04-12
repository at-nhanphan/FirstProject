package com.example.naunem.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.SqliteUserAdapter;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * SQLiteShowListActivity class
 * Created by naunem on 20/03/2017.
 */

@EActivity(R.layout.activity_list_user)
public class SQLiteShowListActivity extends AppCompatActivity {
    @ViewById(R.id.recyclerViewListUser)
    protected RecyclerView mRecyclerView;
    private SqliteUserAdapter mAdapter;
    private UserDatabase mDb = new UserDatabase(this);
    private ArrayList<User> mUsers;

    @AfterViews
    void init() {
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(ln);
        mUsers = mDb.getAllUsers();
        mAdapter = new SqliteUserAdapter(this, mUsers);
        mRecyclerView.setAdapter(mAdapter);
    }
}
