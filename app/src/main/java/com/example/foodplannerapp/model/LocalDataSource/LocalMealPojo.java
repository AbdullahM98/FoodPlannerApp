package com.example.foodplannerapp.model.LocalDataSource;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodplannerapp.model.MealPojo;

import java.io.Serializable;

@Entity(tableName = "meal_table")
public class LocalMealPojo extends MealPojo implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "mealId")
    private String mealId ;

    @ColumnInfo(name ="mealName")
    private String mealName;
    @ColumnInfo(name ="imgUrl")
    private String imgUrl;


    public LocalMealPojo(@NonNull String mealId, String mealName, String imgUrl) {
        this.mealName = mealName;
        this.imgUrl = imgUrl;
        this.mealId = mealId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
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
