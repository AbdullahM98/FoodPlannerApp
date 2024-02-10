package com.example.foodplannerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calender_table")
public class LocalCalenderPojo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="meal_id")
    private String mealId ;
    @ColumnInfo(name="date")
    private String date ;
    @ColumnInfo(name="mealName")
    String mealName;
    @ColumnInfo(name="mealImg")
    String imgUrl;
    @ColumnInfo(name = "user_id")
    private  String userId ;
    public LocalCalenderPojo(String userId, String mealId, String date) {
        this.userId = userId;
        this.mealId = mealId;
        this.date = date;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;

    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
