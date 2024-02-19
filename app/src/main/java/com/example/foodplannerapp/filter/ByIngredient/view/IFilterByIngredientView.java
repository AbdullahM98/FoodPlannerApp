package com.example.foodplannerapp.filter.ByIngredient.view;

import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

public interface IFilterByIngredientView {
    void updateList(List<MealPojo> mealsList);
        void showError(String msg);
}
