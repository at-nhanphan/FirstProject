package com.example.naunem.firstproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naunem on 05/04/2017.
 */

public class SOAnswersResponse {
    @SerializedName("items")
    private ArrayList<Item> items;
    @SerializedName("has_more")
    private boolean hasMore;
    @SerializedName("quota_max")
    private int quotaMax;
    @SerializedName("quota_remaining")
    private int quotaRemaining;

    public SOAnswersResponse(ArrayList<Item> items, boolean hasMore, int quotaMax, int quotaRemaining) {
        this.items = items;
        this.hasMore = hasMore;
        this.quotaMax = quotaMax;
        this.quotaRemaining = quotaRemaining;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(int quotaMax) {
        this.quotaMax = quotaMax;
    }

    public int getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }
}
