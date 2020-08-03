package com.example.reshop.Models;

import com.google.firebase.database.ServerValue;

public class Shoe {


    private String shoeKey;
    private String title;
    private String description;
    private String picture;
    private String userId;
    private String userPhoto;
    private Object timeStamp ;


    public Shoe(String title, String description, String picture, String userId, String userPhoto) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.userId = userId;
        this.userPhoto = userPhoto;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    // make sure to have an empty constructor inside ur model class
    public Shoe() {
    }


    public String getShoeKey() {
        return shoeKey;
    }

    public void setShoeKey(String shoeKey) {
        this.shoeKey = shoeKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
