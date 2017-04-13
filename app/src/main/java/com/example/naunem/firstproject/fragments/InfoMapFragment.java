package com.example.naunem.firstproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.MarkerData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * InfoMapFragment class
 * Created by naunem on 31/03/2017.
 */

@EFragment(R.layout.item_layout_map_info)
public class InfoMapFragment extends Fragment {

    @ViewById(R.id.tvName)
    TextView mTvName;
    @ViewById(R.id.tvAddress)
    TextView mTvAddress;

    private int mPosition = getArguments().getInt("mPosition");
    private final ArrayList<MarkerData> markers = MockData.getAllMarkers();

    @AfterViews
    void init() {
        mTvName.setText(markers.get(mPosition).getTitle());
        mTvAddress.setText(markers.get(mPosition).getLatitude() + ", " + markers.get(mPosition).getLongitude());
    }

    public static InfoMapFragment newInstance(int position) {
        InfoMapFragment fragment = new InfoMapFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mPosition", position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
