package com.example.foodplannerapp.search.presenter;

import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.Repositories.ISearchCallBack;
import com.example.foodplannerapp.model.Repositories.ISearchRemoteServices;
import com.example.foodplannerapp.search.view.ISearchView;

import java.util.List;

public class SearchPresenterImp implements ISearchPresenter , ISearchCallBack {

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
    public void searchMealByName(String mealName) {
        repo.searchMealByName(this,mealName);
    }

    @Override
    public void filterMealByCategory(String mealName) {
        repo.filterByCategory(this,mealName);
    }

    @Override
    public void filterMealByIngredient(String mealName) {
        repo.filterByIngredient(this,mealName);
    }

    @Override
    public void filterMealByCountry(String mealName) {
        repo.filterByCountry(this,mealName);
    }

    @Override
    public void onSearchSucessResult(List<MealPojo> searchResultMeals) {
        view.updateList(searchResultMeals);
    }

    @Override
    public void onFailure(String msg) {
        view.showError(msg);
    }


}
