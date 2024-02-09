package com.example.foodplannerapp.model.RemoteData;

import android.util.Log;

import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.Repositories.ISearchCallBack;
import com.example.foodplannerapp.model.Repositories.NetworkCallBack;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootMeal;
import com.example.foodplannerapp.utils.Constants;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource  {

    private Retrofit retrofit;
    private ApiServices apiServices;
    List<MealPojo> cachedMealsList;
    private static RemoteDataSource instance;
    private RemoteDataSource(){
        Log.d("TAG", "RemoteDataSource: constructor Remote ");
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
        apiServices = retrofit.create(ApiServices.class);

    }
    public static RemoteDataSource getInstance(){
        if(instance==null){
            instance = new RemoteDataSource();
        }
        return instance;
    }
    public void getCategoryCall(NetworkCallBack networkCallBack){


        Single<RootCategories> randomObservable = apiServices.getCategories();
        randomObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
            networkCallBack.onSuccessResult(item.getCategories());
        },throwable -> {
            networkCallBack.onFailedResult(throwable.getMessage());
        });

//        call.enqueue(new Callback<RootCategories>() {
//            @Override
//            public void onResponse(Call<RootCategories> call, Response<RootCategories> response) {
//                networkCallBack.onSuccessResult(response.body().getCategories());
//                Log.d("TAG", "onResponse: "+response.body());
//            }
//
//
//
//            @Override
//            public void onFailure(Call<RootCategories> call, Throwable t) {
//                networkCallBack.onFailedResult(t.getMessage());
//                Log.d("TAG", "onFailure: "+t.getMessage());
//            }
//        });

    }
    public void getRandomMealCall(NetworkCallBack networkCallBack){
        Log.d("TAG", "getRandomMealCall: ");
        Single<RootMeal> randomObservable = apiServices.getRandomMeal();
        randomObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
           networkCallBack.onSuccessRandomMeal(item.getMeals());
        },throwable -> {
            networkCallBack.onFailedResult(throwable.getMessage());
        });
//        call.enqueue(new Callback<RootMeal>() {
//            @Override
//            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
//                if(response.isSuccessful()){
//                    networkCallBack.onSuccessRandomMeal(response.body().getMeals());
//                    Log.d("TAG", "onResponse: "+ response.body().getMeals().get(0).getIdMeal());
//                }
//              //  Log.d("TAG", "onResponse: "+response.body().getMealList().get(0).strMeal);
//
//            }
//
//            @Override
//            public void onFailure(Call<RootMeal> call, Throwable t) {
//                Log.d("TAG", "onFailure: ");
//                networkCallBack.onFailedResult(t.getMessage());
//            }
//        });
    }

    public void searchMealByName(ISearchCallBack networkCallBack , String mealSearchName){
        Single<RootMeal> searchObservable = apiServices.searchMealByName(mealSearchName);
        searchObservable.filter(query ->!mealSearchName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item->{
                    networkCallBack.onSearchSucessResult(item.getMeals());
                },throwable -> {
                    networkCallBack.onFailure(throwable.getMessage());
                }
        );
    }

    public void filterMealByCategory(ISearchCallBack searchCallBack , String catFilterName){
        Single<RootMeal> filterObservable = apiServices.filterByCategory(catFilterName);
        filterObservable.filter(query->!catFilterName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    searchCallBack.onSearchSucessResult(item.getMeals());
                },throwable -> {
                    searchCallBack.onFailure(throwable.getMessage());
                });
    }

    public void filterMealByIng(ISearchCallBack searchCallBack, String filterName){
        Single<RootMeal> filterObservable = apiServices.filterByIngredient(filterName);
        filterObservable.filter(query->!filterName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    searchCallBack.onSearchSucessResult(item.getMeals());
                },throwable -> {
                    searchCallBack.onFailure(throwable.getMessage());
                });
    }
    public void filterMealByCountry(ISearchCallBack searchCallBack, String filterName){
        Single<RootMeal> filterObservable = apiServices.filterByCountry(filterName);
        filterObservable.filter(query->!filterName.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    searchCallBack.onSearchSucessResult(item.getMeals());
                },throwable -> {
                    searchCallBack.onFailure(throwable.getMessage());
                });
    }
    public void getMealByID(ISearchCallBack searchCallBack , String id){
        Single<RootMeal> filterObservable = apiServices.filterByCountry(id);
        filterObservable.filter(query->!id.isEmpty()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    searchCallBack.onSearchSucessResult(item.getMeals());
                },throwable -> {
                    searchCallBack.onFailure(throwable.getMessage());
                });
    }
}
