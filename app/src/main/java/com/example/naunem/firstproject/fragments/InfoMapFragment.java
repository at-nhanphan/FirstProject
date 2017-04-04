package com.example.naunem.firstproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.MarkerData;

import java.util.ArrayList;

/**
 *
 * Created by naunem on 31/03/2017.
 */

public class InfoMapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_layout_map_info, container, false);
        TextView mTvName = (TextView) view.findViewById(R.id.tvName);
        TextView mTvAddress = (TextView) view.findViewById(R.id.tvAddress);

        int position = getArguments().getInt("position");
        ArrayList<MarkerData> markers = MockData.getAllMarkers();
        mTvName.setText(markers.get(position).getTitle());
        mTvAddress.setText(markers.get(position).getLatitude() + ", " + markers.get(position).getLongitude());
        return view;
    }

    public static InfoMapFragment newInstance(int position) {
        InfoMapFragment fragment = new InfoMapFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
