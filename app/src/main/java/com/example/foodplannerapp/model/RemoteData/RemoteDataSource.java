package com.example.foodplannerapp.model.RemoteData;

import android.util.Log;

import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RootArea;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootIngredient;
import com.example.foodplannerapp.model.RootMeal;
import com.example.foodplannerapp.utils.Constants;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
    public Single<RootCategories> getCategoryCall(){


        Single<RootCategories> catObservable = apiServices.getCategories();
        return catObservable;


    }
    public  Single<RootMeal> getRandomMealCall(){
        Log.d("TAG", "getRandomMealCall: ");
        Single<RootMeal> randomObservable = apiServices.getRandomMeal();
        return randomObservable;
    }

    public Single<RootArea> getAllMealsByCountry(){
        return apiServices.listAllMealsByCountry();
    }
    public Single<RootMeal> searchMealByName( String mealSearchName){
        Single<RootMeal> searchObservable = apiServices.searchMealByName(mealSearchName);
        return searchObservable ;

    }

    public Single<RootMeal>  filterMealByCategory( String catFilterName){
        Single<RootMeal> filterObservable = apiServices.filterByCategory(catFilterName);

        return filterObservable;
    }

    public Single<RootMeal> filterMealByIng( String filterName){
        Single<RootMeal> filterObservable = apiServices.filterByIngredient(filterName);

        return filterObservable;
    }
    public Single<RootMeal> filterMealByCountry( String filterName){
        Single<RootMeal> filterObservable = apiServices.filterByCountry(filterName);

        return filterObservable;
    }
    public Single<RootMeal> getMealByID( String id){
        Log.d("TAGG", "getMealByID: "+id);
        Single<RootMeal> getMealByIDObservable = apiServices.getMealById(id);
        return  getMealByIDObservable;

   }
    public Single<RootIngredient> ListAllIngredients(){
        Log.d("TAGG", "getMealByID: ");
        Single<RootIngredient> getMealByIDObservable = apiServices.listAllMealsByIngredient();
        return  getMealByIDObservable;

    }
}
