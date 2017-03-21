package com.example.naunem.firstproject.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by naunem on 20/03/2017.
 */

public class SqliteUser implements Parcelable {
    int id;
    String name;

    public SqliteUser(){

    }
    public SqliteUser(String name) {
        this.name = name;
    }

    public SqliteUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected SqliteUser(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<SqliteUser> CREATOR = new Creator<SqliteUser>() {
        @Override
        public SqliteUser createFromParcel(Parcel in) {
            return new SqliteUser(in);
        }

        @Override
        public SqliteUser[] newArray(int size) {
            return new SqliteUser[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
