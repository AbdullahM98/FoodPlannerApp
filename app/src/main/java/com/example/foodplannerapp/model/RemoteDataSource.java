package com.example.foodplannerapp.model;

import com.example.foodplannerapp.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RemoteDataSource  {

    private Retrofit retrofit;
    private  ApiServices apiServices;

    public RemoteDataSource(){
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).build();

    }

    public void makeServiceCall(NetworkCallBack networkCallBack){
        apiServices = retrofit.create(ApiServices.class);

        Call<List<Category>> call = apiServices.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                networkCallBack.onSuccessResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                networkCallBack.onFailedResult(t.getMessage());
            }
        });

    }
}
