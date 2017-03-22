package com.example.naunem.firstproject.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.fragments.LandscapeFragment;
import com.example.naunem.firstproject.fragments.ListUserFragment;

/**
 * Created by naunem on 22/03/2017.
 */

public class FragmentDemoActivity extends AppCompatActivity {

    LandscapeFragment landscapeFragment = new LandscapeFragment();
    ListUserFragment listFragment = new ListUserFragment();
    private boolean mIsFirstLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);
        if (!mIsFirstLoad) {
            getFragmentManager().beginTransaction().replace(R.id.flContainer, listFragment).commit();
            mIsFirstLoad = true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Log.d("1111", "onConfigurationChanged: " + newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.flContainer, landscapeFragment);
        } else {
            transaction.replace(R.id.flContainer, listFragment);
        }
        transaction.commit();
    }
}
