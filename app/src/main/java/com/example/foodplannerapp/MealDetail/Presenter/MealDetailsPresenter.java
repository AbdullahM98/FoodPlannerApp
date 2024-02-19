package com.example.foodplannerapp.MealDetail.Presenter;

import android.util.Log;

import com.example.foodplannerapp.model.LocalCalenderPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.MealDetail.View.IMealDetailsView;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.Repositories.CalenderRepo;
import com.example.foodplannerapp.model.Repositories.FavoriteRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenter implements IMealsPresenter {

    LocalServices repo ;
    CalenderRepo calenderRepo ;

    IMealDetailsView view ;
    FavoriteRepo favoriteRepo;

    public MealDetailsPresenter(LocalServices repo, IMealDetailsView view ,CalenderRepo calenderRepo,FavoriteRepo favoriteRepo) {
        this.repo = repo;
        this.view = view;
        this.calenderRepo = calenderRepo ;
        this.favoriteRepo = favoriteRepo;
    }


    @Override
    public void addToFav(MealPojo mealPojo) {
       // Log.d("TAG", "AddtoFav: ."+mealPojo.getMealId());
        LocalMealPojo localMealPojo = new LocalMealPojo(mealPojo.getIdMeal(), mealPojo.getStrMeal(), mealPojo.getStrMealThumb());
        Single<Long> single= repo.addMeal(localMealPojo);
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
            view.showToast("Added to Favorite");
        });


        favoriteRepo.addMealToFav(mealPojo);
    }

    @Override
    public void removeFromFav(MealPojo mealPojo) {
        LocalMealPojo localMealPojo = new LocalMealPojo(mealPojo.getIdMeal(), mealPojo.getStrMeal(), mealPojo.getStrMealThumb());

        Single<Integer> single= repo.removeMeal(localMealPojo.getMealId());
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
            view.showToast("Removed from Favortite");
        });
    }
    @Override
    public void addMealToCalender(String date , MealPojo meal){
        calenderRepo.AddToCalender(date,meal);
        LocalCalenderPojo localCalenderPojo = new LocalCalenderPojo("1",meal.getIdMeal(),date);
        localCalenderPojo.setMealName(meal.getStrMeal());
        localCalenderPojo.setImgUrl(meal.getStrMealThumb());

        Single<Long> single=  repo.addMealToCalender(localCalenderPojo);;
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item->{
                    view.showToast("Added to Calender");
                }
        );

    }


}
