package com.example.foodplannerapp.search.presenter;

public interface ISearchPresenter {
    void searchMealByName(String mealName);
    void filterMealByCategory(String mealName);
    void filterMealByIngredient(String mealName);
    void filterMealByCountry(String mealName);
    void getMealById(String mealId);
    

}
