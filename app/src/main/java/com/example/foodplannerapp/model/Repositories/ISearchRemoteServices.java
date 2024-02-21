package com.example.foodplannerapp.model.Repositories;

import com.example.foodplannerapp.model.RootMeal;

import io.reactivex.rxjava3.core.Single;


public interface ISearchRemoteServices {
    Single<RootMeal> searchMealByName(String mealName);
    Single<RootMeal> filterByCategory( String categoryName );


    Single<RootMeal> filterByCountry( String areaName );

    Single<RootMeal> filterByIngredient( String categoryName );
    Single<RootMeal> getMealById(String mealId);
}
