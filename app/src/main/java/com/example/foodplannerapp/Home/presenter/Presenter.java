package com.example.foodplannerapp.Home.presenter;

import android.util.Log;

import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.Repositories.IHomeRemoteServices;
import com.example.foodplannerapp.home.View.HomeViewInterface;
import com.example.foodplannerapp.model.RootArea;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootIngredient;
import com.example.foodplannerapp.model.RootMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Presenter implements IPresenter {
   HomeViewInterface categoryView;
   LocalServices localServices;
    IHomeRemoteServices repo ;

    public Presenter(HomeViewInterface categoryView , IHomeRemoteServices repo , LocalServices localServices) {

        this.repo = repo;
        this.categoryView = categoryView;
        this.localServices = localServices;

    }



    @Override
    public void getData() {
        Single<RootCategories> categoriesObs = repo.getAllCategories();
        categoriesObs.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
                    // updateView
                    categoryView.showData(item.getCategories());
                },throwable -> {
                    categoryView.onFailure(throwable.getMessage());
                }

                );

    }
    @Override
    public void getRandomMeal(){
        Single<RootMeal> randomMeal = repo.getRandomMeal();
        randomMeal.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
                    // updateView
            categoryView.showMeal(item.getMeals());
                },throwable -> {
                    categoryView.onFailure(throwable.getMessage());
                }

        );
    }



//    @Override
//    public void getMealsIngredients() {
//
//    }

     @Override
    public void getMealsByCountry() {
        Single<RootArea> mealsByCountry = repo.listAllMealsByCountry();
        mealsByCountry.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
                categoryView.showCountry(item.getAreas());

                },throwable -> {
                    categoryView.onFailure(throwable.getMessage());

                });
    }

    @Override
    public void getIngredients() {
        Single<RootIngredient> ingredientSingle = repo.listAllMealsByIngredients();
        ingredientSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item->{
                    categoryView.showIng(item.getIngredientPojos());
                    Log.d("TAGG", "getIngredients: "+item.getIngredientPojos().size());
                },throwable -> {
                    categoryView.onFailure(throwable.getMessage());
                }
        );
    }
}
