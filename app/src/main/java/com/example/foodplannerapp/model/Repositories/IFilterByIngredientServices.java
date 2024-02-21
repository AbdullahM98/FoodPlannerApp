package com.example.foodplannerapp.model.Repositories;

import com.example.foodplannerapp.model.RootMeal;

import io.reactivex.rxjava3.core.Single;

public interface IFilterByIngredientServices {
    Single<RootMeal> filterByIngredient(String categoryName );

}
