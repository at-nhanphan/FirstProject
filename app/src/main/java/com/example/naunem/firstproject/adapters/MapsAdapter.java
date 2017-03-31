package com.example.naunem.firstproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.naunem.firstproject.fragments.InfoMapFragment;
import com.example.naunem.firstproject.models.MarkerData;

import java.util.ArrayList;

/**
 *
 * Created by naunem on 31/03/2017.
 */

public class MapsAdapter extends FragmentStatePagerAdapter {
    private ArrayList<MarkerData> mMarkerDatas = new ArrayList<>();
    public MapsAdapter(FragmentManager fm, ArrayList<MarkerData> markerDatas) {
        super(fm);
        mMarkerDatas = markerDatas;
    }

    @Override
    public Fragment getItem(int position) {
        return new InfoMapFragment().newInstance(position);
    }

    @Override
    public int getCount() {
        return mMarkerDatas.size();
    }
}
