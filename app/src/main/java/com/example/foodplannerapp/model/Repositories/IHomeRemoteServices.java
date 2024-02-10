package com.example.foodplannerapp.model.Repositories;

public interface IHomeRemoteServices {
    public void getAllCategories(NetworkCallBack networkCallBack);
    void getRandomMeal(NetworkCallBack networkCallBack);

}
