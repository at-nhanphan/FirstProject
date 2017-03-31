package com.example.naunem.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.MapsAdapter;
import com.example.naunem.firstproject.fragments.MapsFragment;
import com.example.naunem.firstproject.models.MarkerData;

import java.util.ArrayList;

/**
 *
 * Created by naunem on 31/03/2017.
 */

public class MapsActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<MarkerData> markerDatas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        MapsFragment mapsFragment = new MapsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flMaps, mapsFragment).commit();

        markerDatas = MockData.getAllMarkers();
        mViewPager.setPageMargin(20);
        MapsAdapter mapsAdapter = new MapsAdapter(getSupportFragmentManager(), markerDatas);
        mViewPager.setAdapter(mapsAdapter);
    }
}
