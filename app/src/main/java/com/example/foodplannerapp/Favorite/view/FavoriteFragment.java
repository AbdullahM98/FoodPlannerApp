package com.example.foodplannerapp.Favorite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RealTimeDB.RealTimeDB;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.FavoriteRepo;
import com.example.foodplannerapp.model.Repositories.Repository;
import com.example.foodplannerapp.Favorite.Presenter.FavoritePresenter;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment  implements IFavoriteView , onRemoveClickListener {
    List<LocalMealPojo> meals;
    RecyclerView favRecyView;
    LinearLayoutManager layoutManager;
    FavoritePresenter favPresenter ;
    LottieAnimationView lottieAnimationView;
    FavAdapter favAdapter ;
    public FavoriteFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        lottieAnimationView =view.findViewById(R.id.animation_view);
        favRecyView = view.findViewById(R.id.favRecyView);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecyView.setLayoutManager(layoutManager);
        meals = new ArrayList<>();
        favAdapter = new FavAdapter(getActivity().getApplicationContext(),meals,this);
        favRecyView.setAdapter(favAdapter);
        favPresenter = new FavoritePresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())),this, FavoriteRepo.getInstance());
        favPresenter.getAllFav();
       // favPresenter.removeAllMeals();

        if(favAdapter.getMeals().size()==0){
            favRecyView.setVisibility(View.INVISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void updateFavList(List<LocalMealPojo> favMeals) {
        if(favMeals != null ){
                    favAdapter.setList(favMeals);
                    favAdapter.notifyDataSetChanged();
                    favRecyView.setVisibility(View.VISIBLE);
                    if(favMeals.size()!=0){
                        lottieAnimationView.setVisibility(View.INVISIBLE);
                    }
    }}

    @Override
    public void deleteProducts(LocalMealPojo favMeals) {

    }

    @Override
    public void showError(String error) {

    }

    public List<LocalMealPojo> getMeals() {
        return meals;
    }

    public void setMeals(List<LocalMealPojo> meals) {
        this.meals = meals;
    }

    @Override
    public void onRemoveFavClick(MealPojo mealPojo) {
        LocalMealPojo localMealPojo = new LocalMealPojo(mealPojo.getIdMeal(), mealPojo.getStrMeal(), mealPojo.getStrMealThumb());
        favPresenter.removeFromFav(mealPojo);
    }
}