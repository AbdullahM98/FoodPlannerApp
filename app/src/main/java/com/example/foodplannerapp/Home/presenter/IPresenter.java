package com.example.foodplannerapp.Home.presenter;

import com.example.foodplannerapp.model.Category;

import java.util.List;

public interface IPresenter {
    void getData();
    void getRandomMeal();
    void getMealsByCountry();
    void getIngredients();
}
