package com.example.foodplannerapp.calender.Presenter;

import com.example.foodplannerapp.model.LocalCalenderPojo;

public interface ICalenderPresenter {
    void getAllPlans(String date);
    void removePlan(LocalCalenderPojo localCalenderPojo);
}
