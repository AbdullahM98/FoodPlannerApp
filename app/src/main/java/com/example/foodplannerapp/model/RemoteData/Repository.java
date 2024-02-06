package com.example.foodplannerapp.model.RemoteData;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.LocalDataSource.MealDao;

import java.util.List;

public class Repository implements RemoteServices , LocalServices {

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
    public LiveData<List<LocalMealPojo>> getAllFavMeals() {
        MealDao dao = localData.getMealDao();
        LiveData<List<LocalMealPojo>> favMeals = dao.getAllMeals();


        return null;
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
