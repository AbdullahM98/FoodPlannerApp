package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootMeal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
  @GET("categories.php")
  Call<RootCategories> getCategories();

  @GET("random.php")
  Call<RootMeal> getRandomMeal();

}
