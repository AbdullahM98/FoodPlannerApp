package com.example.foodplannerapp.model.Repositories;

import com.example.foodplannerapp.Favorite.Presenter.IFavoritesCallBack;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RealTimeDB.IFavoriteGetter;
import com.example.foodplannerapp.model.RemoteData.RealTimeDB.IFavoriteServices;
import com.example.foodplannerapp.model.RemoteData.RealTimeDB.RealTimeDB;

public class FavoriteRepo {
    private IFavoriteGetter iFavoriteGetter;
    private IFavoriteServices iFavoriteServices;
    private static FavoriteRepo  favRepo;

    public static FavoriteRepo getInstance(){
        if(favRepo ==null){
            favRepo = new FavoriteRepo(RealTimeDB.getInstance(),RealTimeDB.getInstance());
        }
        return favRepo;
    }
    private FavoriteRepo(IFavoriteGetter iFavoriteGetter ,IFavoriteServices iFavoriteServices) {
        this.iFavoriteGetter = iFavoriteGetter;
        this.iFavoriteServices =iFavoriteServices;
    }
    public void getFavoriteMeals(IFavoritesCallBack iFavoritesCallBack){
        iFavoriteGetter.getMealFromFavorite(iFavoritesCallBack);
    }
    public void addMealToFav(MealPojo mealPojo){
        iFavoriteServices.addMealToFavorite(mealPojo);
    }
}
