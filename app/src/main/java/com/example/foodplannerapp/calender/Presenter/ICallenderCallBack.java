package com.example.foodplannerapp.calender.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.LocalCalenderPojo;

import java.util.List;

public interface ICallenderCallBack {
    void onSuccess(LiveData<List<LocalCalenderPojo>> calenderPojos);
    void onFailed(String msg);
}
