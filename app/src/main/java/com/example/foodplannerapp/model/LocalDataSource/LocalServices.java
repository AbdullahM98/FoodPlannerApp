package com.example.foodplannerapp.model.LocalDataSource;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface LocalServices {
    LiveData<List<LocalMealPojo>> getAllFavMeals();
    void addMeal(LocalMealPojo mealPojo);
    void removeMeal(LocalMealPojo mealPojo);
}
