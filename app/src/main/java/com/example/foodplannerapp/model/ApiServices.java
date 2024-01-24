package com.example.foodplannerapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface ApiServices {
  @GET("categories.php")
  Call<List<Category>> getCategories();


}
