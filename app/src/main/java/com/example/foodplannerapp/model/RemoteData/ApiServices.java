package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.CategoriesWrapper;
import com.example.foodplannerapp.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface ApiServices {
  @GET("categories.php")
  Call<CategoriesWrapper> getCategories();


}
