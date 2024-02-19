package com.example.foodplannerapp.home.View;

import com.example.foodplannerapp.model.AreaPojo;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.IngredientPojo;
import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

public interface HomeViewInterface {
        public void showData(List<Category> categories);
        void showMeal(List<MealPojo> meal );
        void showCountry(List<AreaPojo> areaPojos);
        void showIng(List<IngredientPojo> ingredientPojos);
        void onFailure(String error);
}
