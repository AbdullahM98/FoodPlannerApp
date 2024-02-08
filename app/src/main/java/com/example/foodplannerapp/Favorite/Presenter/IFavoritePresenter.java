package com.example.foodplannerapp.Favorite.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

public interface IFavoritePresenter {
    void getAllFav();
    void removeFromFav(LocalMealPojo mealPojo);
}
