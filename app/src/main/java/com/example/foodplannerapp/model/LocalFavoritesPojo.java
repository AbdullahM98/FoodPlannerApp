package com.example.foodplannerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_meal_table")
public class LocalFavoritesPojo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="meal_id")
    private String mealId;

    @ColumnInfo(name = "user_id")
    private String userId;

    public LocalFavoritesPojo(@NonNull String mealId, String userId) {
        this.mealId = mealId;
        this.userId = userId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
