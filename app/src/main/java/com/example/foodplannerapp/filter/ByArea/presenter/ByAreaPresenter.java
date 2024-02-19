package com.example.foodplannerapp.filter.ByArea.presenter;

import com.example.foodplannerapp.filter.ByArea.view.FilterByAreaView;
import com.example.foodplannerapp.model.Repositories.IFilterByAreaServices;
import com.example.foodplannerapp.model.RootMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ByAreaPresenter implements IFilterByAreaPresenter {
IFilterByAreaServices repo;
FilterByAreaView view ;

    public ByAreaPresenter(IFilterByAreaServices repo ,FilterByAreaView view ) {
        this.repo = repo;
        this.view = view ;
    }

    @Override
    public void getByArea(String areaId) {
       Single<RootMeal> areaObs = repo.filterByCountry(areaId);
       areaObs.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
               item->{
                   view.updateList(item.getMeals());
               },throwable -> {
                   view.showError("Couldnt get Meals !");
               });

    }
}
