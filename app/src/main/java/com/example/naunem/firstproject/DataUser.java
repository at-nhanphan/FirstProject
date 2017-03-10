package com.example.naunem.firstproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by naunem on 10/03/2017.
 */

public class DataUser {
    public static ArrayList<User> getDataUser() {
        ArrayList<User> lists = new ArrayList<>();
        Bitmap image = new BitmapFactory().decodeResource(null, R.drawable.ic_user);
        for (int i = 0; i < 100; i++) {
            String name = "User " + i;
            String age = "Age: " + i;
            String gender = null;
            if (i % 2 == 0) {
                gender = "Gender: Male " + i;
            } else {
                gender = "Gender: Female " + i;
            }
            lists.add(new User(image, name, age, gender));
        }
        return lists;
    }
}
