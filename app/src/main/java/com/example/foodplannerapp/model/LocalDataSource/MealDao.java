package com.example.foodplannerapp.model.LocalDataSource;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

@Dao
public interface MealDao {
    @Query("SELECt* FROM favorite_meal_table")
    List<MealPojo>  getAllMeals();
    void addMeal(MealPojo mealPojo);
    void removeMeal(MealPojo mealPojo);
}
