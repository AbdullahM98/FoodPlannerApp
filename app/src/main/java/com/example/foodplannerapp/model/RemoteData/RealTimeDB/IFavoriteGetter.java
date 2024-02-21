package com.example.foodplannerapp.model.RemoteData.RealTimeDB;

import com.example.foodplannerapp.Favorite.Presenter.IFavoritesCallBack;

public interface IFavoriteGetter {
    void getMealFromFavorite(IFavoritesCallBack iFavoritesCallBack);
}
