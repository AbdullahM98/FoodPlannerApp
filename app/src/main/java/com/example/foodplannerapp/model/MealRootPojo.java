package com.example.foodplannerapp.model;

import java.util.List;

public class MealRootPojo {
    private List<MealPojo> mealList;

    public List<MealPojo> getMealList() {
        return mealList;
    }

    public void setMealList(List<MealPojo> mealList) {
        this.mealList = mealList;
    }

    public MealRootPojo(List<MealPojo> mealList) {
        this.mealList = mealList;
    }
}
