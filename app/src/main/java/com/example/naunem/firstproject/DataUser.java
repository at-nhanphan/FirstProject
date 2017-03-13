package com.example.naunem.firstproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by naunem on 10/03/2017.
 */

public class DataUser {
    public static ArrayList<User> getDataUser(Context context) {
        ArrayList<User> lists = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int image = 0;
            String name = "User " + i;
            String age = "Age: " + i;
            String gender = null;
            int favorite = 0;
            boolean isFavorite = false;
            if (i % 2 == 0) {
                image = R.drawable.ic_boy;
                gender = "Gender: Male " + i;
            } else {
                image = R.drawable.ic_girl;
                gender = "Gender: Female " + i;
            }
            favorite = R.drawable.ic_star_border;
            lists.add(new User(image, name, age, gender, favorite, isFavorite));
        }
        return lists;
    }
}
