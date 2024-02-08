package com.example.foodplannerapp.model.Repositories;

public interface RemoteServices {
    public void getAllCategories(NetworkCallBack networkCallBack);
    void getRandomMeal(NetworkCallBack networkCallBack);

}
