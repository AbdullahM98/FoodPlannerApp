package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RootCategories;
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
  Single<List<MealPojo>> searchMealByName(@Query("s") String mealName);
}
