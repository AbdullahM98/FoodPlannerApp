package com.example.foodplannerapp.model.LocalDataSource;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalCalenderPojo;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface LocalServices {
    Flowable<List<LocalMealPojo>> getAllFavMeals();
    Single<Long> addMeal(LocalMealPojo mealPojo);
    Single<Integer> removeMeal(String mealId);
    Single<Long> addMealToCalender(LocalCalenderPojo localCalenderPojo);
    Flowable<List<LocalCalenderPojo>> getAllPlans(String date);
    Single<Integer> removeMealFromCalender(LocalCalenderPojo localCalenderPojo);
}
