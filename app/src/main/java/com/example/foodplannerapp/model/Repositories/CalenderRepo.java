package com.example.foodplannerapp.model.Repositories;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RealTimeDB.RealTimeDB;

import java.util.List;

public class CalenderRepo  {
    private RealTimeDB realTimeDB ;
    private static CalenderRepo instance ;



    public static CalenderRepo getInstance(){
        if(instance ==null){
            instance = new CalenderRepo(RealTimeDB.getInstance());
        }
        return instance;
    }
    private CalenderRepo(RealTimeDB realTimeDB) {
        this.realTimeDB = realTimeDB;

    }
    public void AddToCalender(String selectedDate , MealPojo mealPojo){
        realTimeDB.addMealToCalendar(selectedDate,mealPojo);
    }


}
