package com.example.foodplannerapp.view.Home.Favorite.view;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

import java.util.List;

public interface IFavoriteView {
    void updateFavList(LiveData<List<LocalMealPojo>> favMeals);
    void deleteProducts(LocalMealPojo localMealPojo);
    void showError(String error);
}
