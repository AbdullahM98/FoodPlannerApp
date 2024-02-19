package com.example.foodplannerapp.model.Repositories;

import com.example.foodplannerapp.model.RootArea;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootIngredient;
import com.example.foodplannerapp.model.RootMeal;

import io.reactivex.rxjava3.core.Single;

public interface IHomeRemoteServices {
    Single<RootCategories> getAllCategories();
    Single<RootMeal> getRandomMeal();

    Single<RootArea> listAllMealsByCountry();
    Single<RootIngredient> listAllMealsByIngredients();

}
