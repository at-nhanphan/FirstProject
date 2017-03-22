package com.example.naunem.firstproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.fragments.LandscapeFragment;
import com.example.naunem.firstproject.fragments.ListUserFragment;

/**
 * Created by naunem on 22/03/2017.
 */

public class FragmentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_user);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeFragment landscapeFragment = new LandscapeFragment();
            transaction.replace(R.id.flContainer, landscapeFragment);
        } else {
            ListUserFragment listFragment = new ListUserFragment();
            transaction.replace(R.id.flContainer, listFragment);
        }
        transaction.commit();
    }
}
