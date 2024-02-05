package com.example.foodplannerapp.model.RemoteDataSource;

public interface RepoInterface {
    public void getAllCategories(NetworkCallBack networkCallBack);
    void getRandomMeal(NetworkCallBack networkCallBack);

}
