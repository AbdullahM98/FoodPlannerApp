package com.example.foodplannerapp.view.Home.Favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.view.Home.Favorite.Presenter.IFavoritePresenter;
import com.example.foodplannerapp.view.Home.Favorite.view.IFavoriteView;

import java.util.List;

public class FavoritePresenter implements IFavoritePresenter {
    LocalServices localServices ;
    IFavoriteView iFavoriteView;

    public FavoritePresenter(LocalServices localServices, IFavoriteView iFavoriteView) {
        this.localServices = localServices;
        this.iFavoriteView = iFavoriteView;
    }

    public void getAllFav(){
      iFavoriteView.updateFavList( localServices.getAllFavMeals());
    }

    @Override
    public void removeFromFav(LocalMealPojo mealPojo) {
        localServices.removeMeal(mealPojo);
    }

}
