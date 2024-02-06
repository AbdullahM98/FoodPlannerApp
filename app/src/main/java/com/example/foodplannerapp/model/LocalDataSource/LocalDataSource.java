package com.example.foodplannerapp.model.LocalDataSource;

import android.content.Context;
import android.graphics.Movie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {LocalMealPojo.class},version = 1)
public abstract class LocalDataSource extends RoomDatabase {
    public abstract MealDao getMealDao();
    private static LocalDataSource instance ;

    public static  synchronized LocalDataSource getInstance(Context context){
        if(instance == null){
             instance = Room.databaseBuilder(context.getApplicationContext(),LocalDataSource.class,"mealsdb").build();

        }
        return instance;
    }
}
