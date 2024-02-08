package com.example.foodplannerapp.model.Repositories;

import com.example.foodplannerapp.model.RootMeal;

import io.reactivex.rxjava3.core.Single;


public interface ISearchRemoteServices {
    void searchMealByName(ISearchCallBack searchCallBack, String mealName);
    void filterByCategory(ISearchCallBack searchCallBack, String categoryName );


    void filterByCountry( ISearchCallBack searchCallBack,String areaName );

    void filterByIngredient(ISearchCallBack searchCallBack, String categoryName );
}
