package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.MealPojo;

import java.util.ArrayList;
import java.util.List;

public interface NetworkCallBack {
   void onSuccessResult(List<Category> obj);
   void onSuccessRandomMeal(ArrayList<MealPojo> meals);
   void onFailedResult(String errorMsg);
}
