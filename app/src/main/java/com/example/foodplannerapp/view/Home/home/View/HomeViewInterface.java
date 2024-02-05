package com.example.foodplannerapp.view.Home.home.View;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

public interface HomeViewInterface {
        void showData(List<Category> categories);
        void showMeal(List<MealPojo> meal );
        void onFailure(String error);
}
