package com.example.foodplannerapp.model.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.LocalDataSource.MealDao;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;

import java.util.List;

public class Repository implements RemoteServices, LocalServices ,ISearchRemoteServices{

    private static Repository repo ;
    private RemoteDataSource remoteData;
    private LocalDataSource localData;

    private Repository(RemoteDataSource remoteData , LocalDataSource localData) {

        this.remoteData = remoteData;
        this.localData = localData ;
    }

    public static Repository getInstance(RemoteDataSource remoteData , LocalDataSource localData){
        if (repo == null){
            repo = new Repository(remoteData , localData);
        }
        return repo;
    }


    @Override
    public void getAllCategories(NetworkCallBack networkCallBack) {
        remoteData.getCategoryCall(networkCallBack);
    }

    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        remoteData.getRandomMealCall(networkCallBack);
    }

   @Override
    public void searchMealByName(ISearchCallBack networkCallBack, String mealName) {
        remoteData.searchMealByName(networkCallBack,mealName);
    }

    @Override
    public void filterByCategory(ISearchCallBack searchCallBack, String categoryName) {
        remoteData.filterMealByCategory(searchCallBack,categoryName);
    }

    @Override
    public void filterByCountry(ISearchCallBack searchCallBack, String areaName) {
        remoteData.filterMealByCountry(searchCallBack,areaName);
    }

    @Override
    public void filterByIngredient(ISearchCallBack searchCallBack, String categoryName) {
        remoteData.filterMealByIng(searchCallBack,categoryName);
    }


    @Override
    public LiveData<List<LocalMealPojo>> getAllFavMeals() {
        MealDao dao = localData.getMealDao();
        LiveData<List<LocalMealPojo>> favMeals = dao.getAllMeals();


        return favMeals;
    }

    @Override
    public void addMeal(LocalMealPojo mealPojo) {
        MealDao dao = localData.getMealDao();
        Log.d("TAG", "add This Meal: "+mealPojo.getMealId()+mealPojo.getMealName());
        new Thread(new Runnable() {
            @Override
            public void run() {

                dao.addMeal(mealPojo);
            }
        }).start();
    }

    @Override
    public void removeMeal(LocalMealPojo mealPojo) {
        MealDao dao = localData.getMealDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.removeMeal(mealPojo);
            }
        }).start();
    }

}
