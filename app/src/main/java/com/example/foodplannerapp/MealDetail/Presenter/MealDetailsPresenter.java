package com.example.foodplannerapp.view.Home.MealDetail.Presenter;

import android.util.Log;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.view.Home.MealDetail.View.IMealDetailsView;
import com.example.foodplannerapp.view.Home.MealDetail.View.OnClickListener;

public class MealDetailsPresenter implements IMealsPresenter {

    LocalServices repo ;

    IMealDetailsView view ;

    public MealDetailsPresenter(LocalServices repo, IMealDetailsView view) {
        this.repo = repo;
        this.view = view;
    }


    @Override
    public void addToFav(LocalMealPojo mealPojo) {
        Log.d("TAG", "AddtoFav: ."+mealPojo.getMealId());
        repo.addMeal(mealPojo);
    }

    @Override
    public void removeFromFav(LocalMealPojo mealPojo) {

        repo.removeMeal(mealPojo);
    }
}
