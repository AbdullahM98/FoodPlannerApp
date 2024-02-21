package com.example.foodplannerapp.Favorite.Presenter;

import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalServices;
import com.example.foodplannerapp.Favorite.Presenter.IFavoritePresenter;
import com.example.foodplannerapp.Favorite.view.IFavoriteView;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.Repositories.FavoriteRepo;
import com.example.foodplannerapp.model.RootMeal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter implements IFavoritePresenter , IFavoritesCallBack {
    LocalServices localServices ;
    FavoriteRepo repo ;
    IFavoriteView iFavoriteView;

    public FavoritePresenter(LocalServices localServices, IFavoriteView iFavoriteView,  FavoriteRepo repo) {
        this.localServices = localServices;
        this.iFavoriteView = iFavoriteView;
        this.repo = repo;
    }

    public void getAllFav(){
      Flowable<List<LocalMealPojo>> flowable= localServices.getAllFavMeals();
      flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
              item->{ iFavoriteView.updateFavList(item);}
      );
        //repo.getFavoriteMeals(this);
    }


    @Override
    public void removeFromFav(MealPojo mealPojo) {
        LocalMealPojo localMealPojo = new LocalMealPojo(mealPojo.getIdMeal(), mealPojo.getStrMeal(), mealPojo.getStrMealThumb());
       Single<Integer> single= localServices.removeMeal(localMealPojo.getMealId());
       single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void removeAllMeals() {
        localServices.removeAllMeals();
    }

    @Override
    public void onSuccess(RootMeal rootMeal) {
        List<LocalMealPojo> mealPojoList = new ArrayList<>();
        rootMeal.getMeals().forEach(item->{
            LocalMealPojo localMealPojo= new LocalMealPojo( item.getIdMeal(),item.getStrMeal(),item.getStrMealThumb() );
            mealPojoList.add(localMealPojo);
        });


    }

    @Override
    public void onFailure(String error) {

    }
}
