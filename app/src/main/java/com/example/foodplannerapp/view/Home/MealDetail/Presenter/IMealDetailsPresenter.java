package com.example.foodplannerapp.view.Home.MealDetail.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;

public interface IMealDetailsPresenter {
    void onFavClick(LocalMealPojo mealPojo);
    void onRemoveFavClick(LocalMealPojo mealPojo);
    void onCalenderClick(LocalMealPojo mealPojo);
    void onRemoveCalendereClick(LocalMealPojo mealPojo);

}
