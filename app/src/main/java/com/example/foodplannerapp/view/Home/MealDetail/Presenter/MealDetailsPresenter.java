package com.example.foodplannerapp.view.Home.MealDetail.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.view.Home.MealDetail.View.IMealDetailsView;

public class MealDetailsPresenter implements IMealDetailsPresenter {

    LocalServices repo ;

    IMealDetailsView view ;

    public MealDetailsPresenter(LocalServices repo, IMealDetailsView view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void onFavClick(LocalMealPojo mealPojo) {
        repo.addMeal(mealPojo);
        view.showToast("Added to Favorite");
    }

    @Override
    public void onRemoveFavClick(LocalMealPojo mealPojo) {
        repo.removeMeal(mealPojo);
    }

    @Override
    public void onCalenderClick(LocalMealPojo mealPojo) {

    }

    @Override
    public void onRemoveCalendereClick(LocalMealPojo mealPojo) {

    }
}
