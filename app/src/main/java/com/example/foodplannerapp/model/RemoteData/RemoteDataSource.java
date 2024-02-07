package com.example.foodplannerapp.model.RemoteData;

import android.util.Log;

import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.model.RootMeal;
import com.example.foodplannerapp.utils.Constants;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
        randomObservable.subscribeOn(Schedulers.io()).subscribe(item->{
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



}
