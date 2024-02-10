package com.example.foodplannerapp.model.RemoteData.RealTimeDB;

import com.example.foodplannerapp.model.MealPojo;

public interface IFavoriteServices {
    void addMealToFavorite(MealPojo mealId );
    void removeMealFromFav(String mealId);
}
