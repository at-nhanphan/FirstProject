package com.example.naunem.firstproject;

import android.graphics.Bitmap;

/**
 * Created by naunem on 10/03/2017.
 */

public class User {
    private Bitmap image;
    private String name;
    private String age;
    private String gender;

    public User(Bitmap image, String name, String age, String gender) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
