package com.example.foodplannerapp.view.Home.Favorite.view;

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

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.RemoteData.Repository;
import com.example.foodplannerapp.view.Home.Favorite.presenter.FavoritePresenter;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment  implements IFavoriteView , onRemoveClickListener {
    List<LocalMealPojo> meals;
    RecyclerView favRecyView;
    LinearLayoutManager layoutManager;
    FavoritePresenter favPresenter ;

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
        favRecyView = view.findViewById(R.id.favRecyView);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecyView.setLayoutManager(layoutManager);
        meals = new ArrayList<>();
        favAdapter = new FavAdapter(getActivity().getApplicationContext(),meals,this);
        favRecyView.setAdapter(favAdapter);
        favPresenter = new FavoritePresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())),this);
        favPresenter.getAllFav();
        return view;
    }

    @Override
    public void updateFavList(LiveData<List<LocalMealPojo>> favMeals) {
        if(favMeals != null ){
            favMeals.observe(this, new Observer<List<LocalMealPojo>>() {
                @Override
                public void onChanged(List<LocalMealPojo> localMealPojos) {
                    favAdapter.setList(localMealPojos);
                    favAdapter.notifyDataSetChanged();
                }
            });
        }else{
            Log.d("TAG", "NoData : ");
        }
    }

    @Override
    public void deleteProducts(LocalMealPojo localMealPojo) {

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
    public void onRemoveFavClick(LocalMealPojo mealPojo) {
        favPresenter.removeFromFav(mealPojo);
    }
}