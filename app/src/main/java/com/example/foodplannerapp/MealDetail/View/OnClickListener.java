package com.example.foodplannerapp.MealDetail.View;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

public interface OnClickListener {
    void onFavClick(LocalMealPojo mealPojo);
    void onRemoveFavClick(LocalMealPojo mealPojo);
    void onCalenderClick(LocalMealPojo mealPojo);
    void onRemoveCalendereClick(LocalMealPojo mealPojo);

}
