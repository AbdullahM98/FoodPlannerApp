package com.example.foodplannerapp.MealDetail.View;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

import java.util.List;

public interface IMealDetailsView {
    void showData(LiveData<List<LocalMealPojo>> favMeals);
    void showToast(String msg);
}
