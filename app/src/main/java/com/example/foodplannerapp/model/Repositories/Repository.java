package com.example.foodplannerapp.model.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalCalenderPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.LocalDataSource.MealDao;
import com.example.foodplannerapp.model.RemoteData.RealTimeDB.RealTimeDB;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.RootArea;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootIngredient;
import com.example.foodplannerapp.model.RootMeal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository implements IHomeRemoteServices, LocalServices ,ISearchRemoteServices,IFilterByIngredientServices,IFilterByAreaServices{

    private static Repository repo ;
    private RemoteDataSource remoteData;
    private LocalDataSource localData;
    private RealTimeDB realTimeDB ;


    private Repository(RemoteDataSource remoteData , LocalDataSource localData) {

        this.remoteData = remoteData;
        this.localData = localData ;
        realTimeDB = RealTimeDB.getInstance();
    }

    public static Repository getInstance(RemoteDataSource remoteData , LocalDataSource localData){
        if (repo == null){
            repo = new Repository(remoteData , localData);
        }
        return repo;
    }


    @Override
    public Single<RootCategories> getAllCategories() {
       return remoteData.getCategoryCall();
    }

    @Override
    public  Single<RootMeal> getRandomMeal() {
       return remoteData.getRandomMealCall();
    }

    @Override
    public Single<RootArea> listAllMealsByCountry() {
        return  remoteData.getAllMealsByCountry();
    }

    @Override
    public Single<RootIngredient> listAllMealsByIngredients() {
        return remoteData.ListAllIngredients();
    }

    @Override
    public Single<RootMeal> searchMealByName( String mealName) {
       return remoteData.searchMealByName(mealName);
    }

    @Override
    public Single<RootMeal> filterByCategory( String categoryName) {
        return  remoteData.filterMealByCategory(categoryName);
    }

    @Override
    public Single<RootMeal> filterByCountry( String areaName) {
        return remoteData.filterMealByCountry(areaName);
    }

    @Override
    public Single<RootMeal> filterByIngredient( String categoryName) {
        return  remoteData.filterMealByIng(categoryName);
    }

    @Override
    public Single<RootMeal> getMealById( String mealId) {
        return remoteData.getMealByID(mealId);

    }


    @Override
    public Flowable<List<LocalMealPojo>> getAllFavMeals() {
        MealDao dao = localData.getMealDao();
       return dao.getAllMeals();



    }



    @Override
    public  Single<Long> addMeal(LocalMealPojo mealPojo) {
        MealDao dao = localData.getMealDao();
        Log.d("TAG", "add This Meal: "+mealPojo.getMealId()+mealPojo.getMealName());


              return  dao.addMeal(mealPojo);

    }

    @Override
    public  Single<Integer>removeMeal(String mealId) {
        MealDao dao = localData.getMealDao();

              return   dao.removeMeal(mealId);

    }

    @Override
    public  Single<Long> addMealToCalender(LocalCalenderPojo localCalenderPojo) {
        MealDao dao = localData.getMealDao();


              return  dao.addMealToCalender(localCalenderPojo);

    }

    @Override
    public Flowable<List<LocalCalenderPojo>> getAllPlans(String date) {
        MealDao dao = localData.getMealDao();
       return dao.getAllMealPlans(date);

    }

    @Override
    public Single<Integer> removeMealFromCalender(LocalCalenderPojo localCalenderPojo) {
        realTimeDB.removeMealFromCalender(localCalenderPojo.getMealId());
        MealDao dao = localData.getMealDao();
      return  dao.removeMealFromCalender(localCalenderPojo.getMealId());



    }

    @Override
    public Single<Integer> resetFavMeals() {
        MealDao dao = localData.getMealDao();
        return dao.resetFavMeals();
    }

    @Override
    public Single<Integer> resetCalenderMeals() {
        MealDao dao = localData.getMealDao();
        return dao.resetCalenderMeals();
    }

    @Override
    public void removeAllMeals() {
        MealDao dao = localData.getMealDao();
        new Thread(()->{
                dao.deleteAllMeals();
        }).start();
    }


}
