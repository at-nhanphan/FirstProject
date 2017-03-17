package com.example.naunem.firstproject.models;

/**
 * Created by naunem on 16/03/2017.
 */

public class Title extends ItemList {
    private String mTitle;
    private final int VIEW_TITLE = 2;

    public Title() {

    }

    public Title(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int getType() {
        return VIEW_TITLE;
    }
}
