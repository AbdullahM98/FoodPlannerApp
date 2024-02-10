package com.example.foodplannerapp.calender.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.calender.view.ICalenderView;
import com.example.foodplannerapp.model.LocalCalenderPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderPresenter implements ICalenderPresenter,ICallenderCallBack{
    private LocalServices localServices;
    private ICalenderView view ;

    public CalenderPresenter(LocalServices localServices, ICalenderView view) {
        this.localServices = localServices;
        this.view = view;
    }

    @Override
    public void getAllPlans(String date) {
        Flowable<List<LocalCalenderPojo>> flowable =localServices.getAllPlans(date);
        flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item ->{  view.updateList(item);}
        );

    }

    @Override
    public void removePlan(LocalCalenderPojo localCalenderPojo) {
       Single<Integer> single = localServices.removeMealFromCalender(localCalenderPojo);
       single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void onSuccess(LiveData<List<LocalCalenderPojo>> calenderPojos) {

    }

    @Override
    public void onFailed(String msg) {

    }
}
