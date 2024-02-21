package com.example.foodplannerapp.settings.presenter;

import com.example.foodplannerapp.model.Authentication.AuthRepository;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SettingsPresenter implements ISettingsPresenter{
    AuthRepository authRepository;
    LocalServices repo ;

    public SettingsPresenter( AuthRepository authRepository ,LocalServices repo) {
        this.authRepository=authRepository;
        this.repo = repo ;
    }

    @Override
    public void logOut() {
        authRepository.logout();
        Single<Integer> single= repo.resetCalenderMeals();
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        Single<Integer> single2= repo.resetFavMeals();
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();

    }
}
