package com.example.naunem.firstproject.models;

import com.google.gson.annotations.SerializedName;

/**
 * This class is an object Item
 * Created by naunem on 05/04/2017.
 */

public class Item {
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("is_accepted")
    private boolean isAccepted;
    @SerializedName("score")
    private int score;
    @SerializedName("answer_id")
    private double answerId;
    @SerializedName("question_id")
    private double questionId;

    public Item(Owner owner, boolean isAccepted, int score, double answerId, double questionId) {
        this.owner = owner;
        this.isAccepted = isAccepted;
        this.score = score;
        this.answerId = answerId;
        this.questionId = questionId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getAnswerId() {
        return answerId;
    }

    public void setAnswerId(double answerId) {
        this.answerId = answerId;
    }

    public double getQuestionId() {
        return questionId;
    }

    public void setQuestionId(double questionId) {
        this.questionId = questionId;
    }
}
