package com.example.naunem.firstproject;

import android.content.Context;
import android.util.Log;

import com.example.naunem.firstproject.models.ItemList;
import com.example.naunem.firstproject.models.Title;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by naunem on 10/03/2017.
 */

public class MockData {
    public static ArrayList<ItemList> getData(Context context) {
        ArrayList<ItemList> lists = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int rand = random.nextInt(20);
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
            if (i == rand || i == 0) {
                lists.add(new Title("Group A"));
            } else if (rand % 4 == 1) {
                lists.add(new Title("Group B"));
            } else {
                lists.add(new User(image, name, age, gender, favorite, isFavorite));
            }
        }
        return lists;
    }

    public static ItemList getDataById(int position) {
        int image = 0;
        ItemList item;
        String name = "User " + position;
        String age = "Age: " + position;
        String gender = null;
        int favorite = 0;
        boolean isFavorite = false;
        if (position % 2 == 0) {
            image = R.drawable.ic_boy;
            gender = "Gender: Male " + position;
        } else {
            image = R.drawable.ic_girl;
            gender = "Gender: Female " + position;
        }
        favorite = R.drawable.ic_star_border;
        if (position % 5 == 0) {
            item = new Title("Group A");
        } else if (position % 7 == 1) {
            item = new Title("Group B");
        } else {
            item = new User(image, name, age, gender, favorite, isFavorite);
        }
        return item;
    }
}
