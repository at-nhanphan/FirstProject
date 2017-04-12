package com.example.naunem.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.naunem.firstproject.adapters.UserAdapter;
import com.example.naunem.firstproject.interfaces.MyOnClickListener;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_list_user)
public class ListUserActivity extends AppCompatActivity implements MyOnClickListener {

    @ViewById(R.id.imgBack)
    ImageView mImgBack;
    @ViewById(R.id.imgSettings)
    ImageView mImgSettings;
    @ViewById(R.id.recyclerViewListUser)
    RecyclerView mRecyclerViewListUser;
    private UserAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<User> mUsers;
    private final UserDatabase mUserDatabase = new UserDatabase(this);
    @Extra
    boolean isCheck;

    @AfterViews
    void init() {
        mRecyclerViewListUser.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListUser.setLayoutManager(mLayoutManager);
        if (mUserDatabase.getAllUsers().size() == 0) {
            User user = new User("content://com.android.providers.media.documents/document/image%3A57", "jerry", "23", "male");
            mUserDatabase.insertUser(user);
        } else {
            mUsers = mUserDatabase.getAllUsers();
        }
        mAdapter = new UserAdapter(this, mUsers, mRecyclerViewListUser, this);
        mRecyclerViewListUser.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Click(R.id.imgBack)
    void back() {
        finish();
    }

    @Click(R.id.imgSettings)
    void addUser() {
        LayoutAddEditActivity_.intent(this).mValue("add").start();
        finish();
    }

//        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                // Add null , so the adapter will check view_type and show progress bar at bottom
//                mItemLists.add(null);
//                mAdapter.notifyItemInserted(mItemLists.size() - 1);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Remove progress item
//                        mItemLists.remove(mItemLists.size() - 1);
//                        mAdapter.notifyItemRemoved(mItemLists.size());
//                        // Add items
//                        int start = mItemLists.size();
//                        int end = start + 20;
//                        for (int i = start; i < end; i++) {
//                            ListItem item = MockData.getDataById(i);
//                            mItemLists.add(item);
//                            mAdapter.notifyItemInserted(mItemLists.size());
//                        }
//                        mAdapter.setLoaded();
//                    }
//                }, 1000);
//            }
//        });

    @Override
    public void onClickListener(int position) {
        LayoutAddEditActivity_.intent(this).mValue("edit").mUser(mUsers.get(position)).start();
    }
}
