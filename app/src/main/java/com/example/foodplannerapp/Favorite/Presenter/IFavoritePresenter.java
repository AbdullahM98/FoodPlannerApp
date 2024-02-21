package com.example.foodplannerapp.Favorite.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;

public interface IFavoritePresenter {
    void getAllFav();
    void removeFromFav(MealPojo mealPojo);
    void removeAllMeals();
}
