package com.example.naunem.firstproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * MarkerData class
 * Created by naunem on 31/03/2017.
 */

public class MarkerData extends ArrayList<MarkerData> implements Parcelable {
    private double latitude;
    private double longitude;
    private String title;
    private String snippet;

    public MarkerData(double latitude, double longitude, String title, String snippet) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.snippet = snippet;
    }

    private MarkerData(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        title = in.readString();
        snippet = in.readString();
    }

    public static final Creator<MarkerData> CREATOR = new Creator<MarkerData>() {
        @Override
        public MarkerData createFromParcel(Parcel in) {
            return new MarkerData(in);
        }

        @Override
        public MarkerData[] newArray(int size) {
            return new MarkerData[size];
        }
    };

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(title);
        dest.writeString(snippet);
    }
}
