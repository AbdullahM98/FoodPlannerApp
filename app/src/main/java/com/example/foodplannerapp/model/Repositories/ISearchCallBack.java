package com.example.foodplannerapp.model.Repositories;

import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

public interface ISearchCallBack {
    void onSearchSucessResult(List<MealPojo> searchResultMeals);
    void onGetMealById(List<MealPojo> mealPojos);
    void onFailure(String msg);
}
