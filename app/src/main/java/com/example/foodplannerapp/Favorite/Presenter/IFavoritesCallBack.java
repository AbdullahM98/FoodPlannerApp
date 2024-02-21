package com.example.foodplannerapp.Favorite.Presenter;

import com.example.foodplannerapp.model.RootMeal;

public interface IFavoritesCallBack {
    void onSuccess(RootMeal rootMeal);
    void onFailure(String error);

}
