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

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository implements IHomeRemoteServices, LocalServices ,ISearchRemoteServices{

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
    public void getMealById(ISearchCallBack searchCallBack, String mealId) {
        remoteData.getMealByID(searchCallBack,mealId);
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


}
