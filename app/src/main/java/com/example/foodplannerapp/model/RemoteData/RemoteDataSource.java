package com.example.foodplannerapp.model.RemoteData;

import android.util.Log;

import com.example.foodplannerapp.model.CategoriesWrapper;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.utils.Constants;

import java.util.List;

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
    public void makeServiceCall(NetworkCallBack networkCallBack){


        Call<CategoriesWrapper> call = apiServices.getCategories();
        call.enqueue(new Callback<CategoriesWrapper>() {
            @Override
            public void onResponse(Call<CategoriesWrapper> call, Response<CategoriesWrapper> response) {
                networkCallBack.onSuccessResult(response.body().getCategories());
                Log.d("TAG", "onResponse: "+response.body());
            }



            @Override
            public void onFailure(Call<CategoriesWrapper> call, Throwable t) {
                networkCallBack.onFailedResult(t.getMessage());
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });

    }
}
