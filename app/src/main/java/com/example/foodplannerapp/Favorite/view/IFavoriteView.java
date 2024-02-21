package com.example.foodplannerapp.Favorite.view;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

import java.util.List;

public interface IFavoriteView {
    void updateFavList(List<LocalMealPojo> favMeals);
    void deleteProducts(LocalMealPojo localMealPojo);
    void showError(String error);
}
