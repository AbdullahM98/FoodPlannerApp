package com.example.foodplannerapp.MealDetail.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;

public interface IMealsPresenter {
    void addToFav(MealPojo mealPojo);
    void removeFromFav(MealPojo mealPojo);
    void addMealToCalender(String date , MealPojo meal);
}
