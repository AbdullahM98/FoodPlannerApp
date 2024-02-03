package com.example.foodplannerapp.model.RemoteData;

import android.util.Log;

import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootMeal;
import com.example.foodplannerapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource  {

    private Retrofit retrofit;
    private ApiServices apiServices;
    private static RemoteDataSource instance;
    private RemoteDataSource(){
        Log.d("TAG", "RemoteDataSource: constructor Remote ");
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiServices = retrofit.create(ApiServices.class);

    }
    public static RemoteDataSource getInstance(){
        if(instance==null){
            instance = new RemoteDataSource();
        }
        return instance;
    }
    public void getCategoryCall(NetworkCallBack networkCallBack){


        Call<RootCategories> call = apiServices.getCategories();
        call.enqueue(new Callback<RootCategories>() {
            @Override
            public void onResponse(Call<RootCategories> call, Response<RootCategories> response) {
                networkCallBack.onSuccessResult(response.body().getCategories());
                Log.d("TAG", "onResponse: "+response.body());
            }



            @Override
            public void onFailure(Call<RootCategories> call, Throwable t) {
                networkCallBack.onFailedResult(t.getMessage());
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });

    }
    public void getRandomMealCall(NetworkCallBack networkCallBack){
        Log.d("TAG", "getRandomMealCall: ");
        Call<RootMeal> call = apiServices.getRandomMeal();
        call.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                if(response.isSuccessful()){
                    networkCallBack.onSuccessRandomMeal(response.body().getMeals());
                    Log.d("TAG", "onResponse: "+ response.body().getMeals().get(0).getIdMeal());
                }
              //  Log.d("TAG", "onResponse: "+response.body().getMealList().get(0).strMeal);

            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
                networkCallBack.onFailedResult(t.getMessage());
            }
        });
    }
}
