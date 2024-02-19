package com.example.foodplannerapp.filter.ByArea.view;

import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

public interface FilterByAreaView {
    void updateList(List<MealPojo> mealsList);
    void showError(String msg);
}
