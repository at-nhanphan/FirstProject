package com.example.naunem.firstproject.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.fragments.MapsFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 *
 * Created by naunem on 31/03/2017.
 */

@EActivity(R.layout.activity_maps)
public class MapsActivity extends AppCompatActivity {

    @AfterViews
    void init() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flMaps, MapsFragment_.builder().build()).commit();
    }
}
