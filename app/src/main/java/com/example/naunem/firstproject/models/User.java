package com.example.naunem.firstproject.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User class
 * Created by naunem on 10/03/2017.
 */

public class User extends ListItem implements Parcelable {
    private int id;
    private String image;
    private String name;
    private String age;
    private String gender;
    private int favorite;
    private boolean isFavorite;
    private final int VIEW_ITEM = 1;

    /**
     * Constructor
     *
     * @param image
     * @param name
     * @param age
     * @param gender
     */
    public User(String image, String name, String age, String gender, int favorite, boolean isFavorite) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.favorite = favorite;
        this.isFavorite = isFavorite;
    }

    public User() {

    }

    public User(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User(String image, String name, String age, String gender) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User(int id, String image, String name, String age, String gender) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    protected User(Parcel in) {
        id = in.readInt();
        image = in.readString();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Bitmap
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return String gender
     */
    public String getGender() {
        return gender;
    }

    /**
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
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeInt(favorite);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public int getType() {
        return VIEW_ITEM;
    }
}
