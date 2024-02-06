package com.example.foodplannerapp.model.LocalDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

@Dao
public interface MealDao {
    @Query("SELECt* FROM favorite_meal_table")
    LiveData<List<LocalMealPojo>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMeal(LocalMealPojo mealPojo);
    @Delete
    void removeMeal(LocalMealPojo mealPojo);
}
