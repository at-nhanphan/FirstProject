package com.example.naunem.firstproject.models;

/**
 * Created by naunem on 16/03/2017.
 */

public class Title extends ItemList{
    private String mTitle;

    public Title(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    int getType() {
        return 0;
    }
}
