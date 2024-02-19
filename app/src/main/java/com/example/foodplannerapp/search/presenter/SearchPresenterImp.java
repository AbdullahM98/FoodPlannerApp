package com.example.foodplannerapp.search.presenter;

import android.util.Log;

import com.example.foodplannerapp.model.MealPojo;

import com.example.foodplannerapp.model.Repositories.ISearchRemoteServices;
import com.example.foodplannerapp.model.RootMeal;
import com.example.foodplannerapp.search.view.ISearchView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements ISearchPresenter  {

    List<MealPojo> searchResultMeals ;
    ISearchView view ;
    ISearchRemoteServices repo;

    public SearchPresenterImp(ISearchView view, ISearchRemoteServices repo) {
        this.view = view;
        this.repo = repo;
    }

    public List<MealPojo> getSearchResultMeals() {
        return searchResultMeals;
    }

    public void setSearchResultMeals(List<MealPojo> searchResultMeals) {
        this.searchResultMeals = searchResultMeals;
    }
    @Override
    public void getMealById(String mealId) {

        Single<RootMeal> mealObs = repo.getMealById(mealId);
        mealObs.filter(query->!mealId.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    Log.d("TAGG", "getMealByID: "+item.getMeals().size());

                    view.updateSingleMeal(item.getMeals().get(0));
                },throwable -> {
                    view.showError(throwable.getMessage());
                });
    }
    @Override
    public void searchMealByName(String mealName) {

        Single<RootMeal> mealObs = repo.searchMealByName(mealName);
        mealObs.filter(query->!mealName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    Log.d("TAGG", "getMealByID: "+item.getMeals().size());

                    view.updateList(item.getMeals());
                },throwable -> {
                    view.showError(throwable.getMessage());
                });
    }

    @Override
    public void filterMealByCategory(String mealName) {


        Single<RootMeal> mealObs = repo.filterByCategory(mealName);
        mealObs.filter(query->!mealName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    Log.d("TAGG", "getMealByID: "+item.getMeals().size());

                    view.updateList(item.getMeals());
                },throwable -> {
                    view.showError(throwable.getMessage());
                });
    }

    @Override
    public void filterMealByIngredient(String mealName) {

        Single<RootMeal> mealObs =  repo.filterByIngredient(mealName);
        mealObs.filter(query->!mealName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    Log.d("TAGG", "getMealByID: "+item.getMeals().size());

                    view.updateList(item.getMeals());
                },throwable -> {
                    view.showError(throwable.getMessage());
                });
    }

    @Override
    public void filterMealByCountry(String mealName) {

        Single<RootMeal> mealObs =  repo.filterByCountry(mealName);
        mealObs.filter(query->!mealName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    Log.d("TAGG", "getMealByID: "+item.getMeals().size());

                    view.updateList(item.getMeals());
                },throwable -> {
                    view.showError(throwable.getMessage());
                });
    }

//    @Override
//    public void onSearchSucessResult(List<MealPojo> searchResultMeals) {
//        view.updateList(searchResultMeals);
//    }
//
//    @Override
//    public void onGetMealByIdSuccess(List<MealPojo> mealPojos) {
//        Log.d("TAGG", "onGetMealByIdSuccess: ");
//
//    }
//
//    @Override
//    public void onFailure(String msg) {
//        view.showError(msg);
//    }


}
