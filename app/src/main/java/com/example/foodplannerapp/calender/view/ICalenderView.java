package com.example.foodplannerapp.calender.view;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalCalenderPojo;

import java.util.List;

public interface ICalenderView {
    void updateList(List<LocalCalenderPojo> localCalenderPojoList);
}
