package com.example.naunem.firstproject;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by naunem on 10/03/2017.
 */

public class User implements Parcelable {
    private int image;
    private String name;
    private String age;
    private String gender;
    private int favorite;
    private boolean isFavorite;

    /**
     * Constructor
     * @param image
     * @param name
     * @param age
     * @param gender
     */
    public User(int image, String name, String age, String gender, int favorite, boolean isFavorite) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.favorite = favorite;
        this.isFavorite = isFavorite;
    }


    protected User(Parcel in) {
        image = in.readInt();
        name = in.readString();
        age = in.readString();
        gender = in.readString();
        favorite = in.readInt();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     *
     * @return Bitmap
     */
    public int getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return String age
     */
    public String getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     *
     * @return String gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeInt(favorite);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }
}
