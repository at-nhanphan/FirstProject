package com.example.naunem.firstproject.models;

import com.google.gson.annotations.SerializedName;

/**
 * This class init Owner object
 * Created by naunem on 05/04/2017.
 */

public class Owner {
    @SerializedName("user_id")
    private int id;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("display_name")
    private String name;

    public Owner(int id, String userType, String profileImage, String name) {
        this.id = id;
        this.userType = userType;
        this.profileImage = profileImage;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
