package com.example.foodplannerapp.search.view;

import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

public interface ISearchView {
    void updateList(List<MealPojo> searchResultMeals);
    void showError(String error);
}
