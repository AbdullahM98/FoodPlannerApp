package com.example.foodplannerapp.model.RemoteData;

public interface RemoteServices {
    public void getAllCategories(NetworkCallBack networkCallBack);
    void getRandomMeal(NetworkCallBack networkCallBack);
    void searchMealByName(NetworkCallBack networkCallBack,String mealName);
}
