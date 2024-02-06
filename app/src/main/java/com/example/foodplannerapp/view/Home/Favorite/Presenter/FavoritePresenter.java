package com.example.foodplannerapp.view.Home.Favorite.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.view.Home.Favorite.view.IFavoriteView;

import java.util.List;

public class FavoritePresenter {
    LocalServices localServices ;
    IFavoriteView iFavoriteView;

    public FavoritePresenter(LocalServices localServices, IFavoriteView iFavoriteView) {
        this.localServices = localServices;
        this.iFavoriteView = iFavoriteView;
    }

    public LiveData<List<LocalMealPojo>> getAllFav(){
        return localServices.getAllFavMeals();
    }

}
