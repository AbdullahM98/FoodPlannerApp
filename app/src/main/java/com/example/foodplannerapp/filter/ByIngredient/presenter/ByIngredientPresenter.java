package com.example.foodplannerapp.filter.ByIngredient.presenter;

import com.example.foodplannerapp.filter.ByIngredient.view.IFilterByIngredientView;
import com.example.foodplannerapp.model.Repositories.IFilterByIngredientServices;
import com.example.foodplannerapp.model.RootMeal;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ByIngredientPresenter implements IFilterByIngredientPresenter {

        IFilterByIngredientServices repo ;
        IFilterByIngredientView view ;

    public ByIngredientPresenter(IFilterByIngredientServices repo, IFilterByIngredientView view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void getByIngredient(String ingId) {
        Single<RootMeal> ingObs =  repo.filterByIngredient(ingId);
        ingObs.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item->{
                    view.updateList(item.getMeals());
                },throwable -> {
                    view.showError(throwable.getMessage());
                }
        );
    }
}
