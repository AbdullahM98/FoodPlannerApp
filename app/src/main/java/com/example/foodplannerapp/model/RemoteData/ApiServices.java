package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RootArea;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootIngredient;
import com.example.foodplannerapp.model.RootMeal;
import com.google.android.gms.common.data.DataBufferObserver;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
  @GET("categories.php")
  Single<RootCategories> getCategories();

  @GET("random.php")
  Single<RootMeal> getRandomMeal();

  @GET("search.php/{query}")
  Single<RootMeal> searchMealByName(@Query("s") String mealName);
  @GET("filter.php/{query}")
  Single<RootMeal> filterByCategory(@Query("c") String categoryName );

  @GET("filter.php/{query}")
  Single<RootMeal> filterByCountry(@Query("a") String areaName );
  @GET("filter.php/{query}")
  Single<RootMeal> filterByIngredient(@Query("i") String categoryName );
  @GET("lookup.php/{query}")
  Single<RootMeal> getMealById(@Query("i") String MealName );
  @GET("list.php?a=list")
  Single<RootArea> listAllMealsByCountry();

  @GET("list.php?i=list")
  Single<RootIngredient> listAllMealsByIngredient();

}
