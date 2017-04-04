package com.example.naunem.firstproject.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naunem.firstproject.MockData;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.MapsViewPagerAdapter;
import com.example.naunem.firstproject.models.MarkerData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by naunem on 31/03/2017.
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, ViewPager.OnPageChangeListener {

    protected MapView mMapView;
    private ViewPager mViewPager;
    private GoogleMap mMap;
    private Marker mMarker;
    private ArrayList<MarkerData> mListMarkers;
    private MarkerData mMarkerData;
    private Marker mFirstMarker;
    private List<Marker> mInitMarkers = new ArrayList<>();
    private int mLastPosition;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = (MapView) view.findViewById(R.id.mapView);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setPageMargin(20);
        mMapView.onCreate(savedInstanceState);
        mListMarkers = MockData.getAllMarkers();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            Log.e("exeption", "onCreateView: ", e);
        }
        mMapView.getMapAsync(this);

        MapsViewPagerAdapter mapsViewPagerAdapter = new MapsViewPagerAdapter(getActivity().getSupportFragmentManager(), mListMarkers);
        mViewPager.setAdapter(mapsViewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMarkerData = mListMarkers.get(0);
//        mFirstMarker = createMarker(mMarkerData);
//        mFirstMarker.showInfoWindow();
//        mFirstMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_48dp));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMarkerData.getLatitude(), mMarkerData.getLongitude()), 16));

        for (int i = 0; i < mListMarkers.size(); i++) {
            mMarkerData = mListMarkers.get(i);
            Marker marker = createMarker(mMarkerData);
            if (i == 0) {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_48dp));
            } else {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_36dp));
            }
            mInitMarkers.add(marker);
        }

        PolylineOptions lineOptions = new PolylineOptions();
        ArrayList<LatLng> points = new ArrayList<>();
        points.add(new LatLng(mListMarkers.get(0).getLatitude(), mListMarkers.get(0).getLongitude()));
        points.add(new LatLng(mListMarkers.get(3).getLatitude(), mListMarkers.get(3).getLongitude()));
        lineOptions.addAll(points);
        lineOptions.color(Color.RED);
        lineOptions.width(10);

        mMap.addPolyline(lineOptions);

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

            }
        });

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double latitude = (float) location.getLatitude();
        double longitude = (float) location.getLongitude();
        mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                .title("My location").snippet(latitude + ", " + longitude));
        mMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_48dp));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));
        return true;
    }

    protected Marker createMarker(MarkerData markerData) {
        return mMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(markerData.getLatitude(), markerData.getLongitude()))
                .title(markerData.getTitle())
                .snippet(markerData.getSnippet()));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // TODO: 03/04/2017
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for (int i = 0; i < mInitMarkers.size(); i++) {
            if (marker.equals(mInitMarkers.get(i))) {
                mViewPager.setCurrentItem(i);
            }
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mLastPosition = position;
        mMarkerData = mListMarkers.get(position);
//        mFirstMarker.setVisible(false);
//        mMarker.setVisible(false);
        mInitMarkers.get(position).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_48dp));
        LatLng locationChoosed = new LatLng(mMarkerData.getLatitude(), mMarkerData.getLongitude());

//        mMarker = createMarker(mMarkerData);
//        mMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_48dp));
//        mMarker.showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationChoosed, 16));

        for (int i = 0; i < mInitMarkers.size(); i++) {
            if (position == 0) {
                mInitMarkers.get(position).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_48dp));
            }
            if (i != position) {
                mInitMarkers.get(i).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_red_600_36dp));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
