package com.example.foodplannerapp.Home.presenter;

import android.util.Log;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.Repositories.NetworkCallBack;
import com.example.foodplannerapp.model.Repositories.RemoteServices;
import com.example.foodplannerapp.home.View.HomeViewInterface;
import  com.example.foodplannerapp.home.presenter.IPresenter;
import java.util.ArrayList;
import java.util.List;

public class Presenter implements NetworkCallBack ,IPresenter {
    com.example.foodplannerapp.home.View.HomeViewInterface categoryView;
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
