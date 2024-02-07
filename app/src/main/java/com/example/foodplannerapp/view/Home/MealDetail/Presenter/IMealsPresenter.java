package com.example.foodplannerapp.view.Home.MealDetail.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

public interface IMealsPresenter {
    void addToFav(LocalMealPojo mealPojo);
    void removeFromFav(LocalMealPojo mealPojo);

}
