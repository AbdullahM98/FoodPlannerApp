package com.example.foodplannerapp.model.LocalDataSource;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class LocalDataSource extends RoomDatabase {
    public abstract MealDao getMovieDao();
    private static LocalDataSource instance ;

    public static LocalDataSource getInstance(Context context){
        if(instance == null){
             instance = Room.databaseBuilder(context.getApplicationContext(),LocalDataSource.class,"mealsdb").build();

        }
        return instance;
    }
}
