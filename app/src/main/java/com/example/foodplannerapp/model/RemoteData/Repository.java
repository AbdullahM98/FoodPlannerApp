package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.LocalDataSource.MealDao;

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
    public void addMeal(LocalMealPojo mealPojo) {
        MealDao dao = localData.getMealDao();
        dao.addMeal(mealPojo);
    }

    @Override
    public void removeMeal(LocalMealPojo mealPojo) {
        MealDao dao = localData.getMealDao();
        dao.removeMeal(mealPojo);
    }
}
