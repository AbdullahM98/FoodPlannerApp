package com.example.foodplannerapp.view.Home.home.presenter;

import android.util.Log;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.NetworkCallBack;
import com.example.foodplannerapp.model.RemoteData.RemoteServices;
import com.example.foodplannerapp.view.Home.home.View.HomeViewInterface;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements NetworkCallBack , IPresenter{
    HomeViewInterface categoryView;
    RemoteServices repo ;

    public Presenter(HomeViewInterface categoryView , RemoteServices repo) {

        this.repo = repo;
        this.categoryView = categoryView;

    }

    public void onSuccessResult(List<Category> listOfData) {
        Log.d("TAG", "onSuccessResult: "+listOfData);

            categoryView.showData((List<Category>) listOfData);

    }

    @Override
    public void onSuccessRandomMeal(ArrayList<MealPojo> meals) {
        categoryView.showMeal(meals);
    }

    @Override
    public void onFailedResult(String errorMsg) {
        categoryView.onFailure(errorMsg);
    }

    @Override
    public void getData() {
        repo.getAllCategories(this);
       repo.getRandomMeal(this);
    }
}
