package com.example.foodplannerapp.view;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;

import java.util.List;

public interface Communicator {
    void setFavList(List<LocalMealPojo> favMeals);
}
