package com.example.foodplannerapp.model.LocalDataSource;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_meal_table")
public class LocalMealPojo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="mealName")
    private String mealName;
    @ColumnInfo(name ="imgUrl")
    private String imgUrl;


}
