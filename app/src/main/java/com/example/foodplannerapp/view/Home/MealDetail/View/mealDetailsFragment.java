package com.example.foodplannerapp.view.Home.MealDetail.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.RemoteData.Repository;
import com.example.foodplannerapp.view.Home.MealDetail.Presenter.IMealsPresenter;
import com.example.foodplannerapp.view.Home.MealDetail.Presenter.MealDetailsPresenter;

import java.util.List;


public class mealDetailsFragment extends Fragment implements IMealDetailsView , OnClickListener {

    private IMealsPresenter presenter ;
    private TextView mealName , mealCat , mealCountry , ingredientTxt , detailsTxt ;
    private  ImageView mealImg , favBtn ;
    private  MealPojo myMeal;
    private LocalMealPojo localMealPojo;

    private boolean isFavorite ;


    public mealDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_meal_detail, container, false);

        mealName = view.findViewById(R.id.mealNameTxtView);
        mealCat = view.findViewById(R.id.mealCategoryTxtView);
        mealCountry = view.findViewById(R.id.countryTxtView);
        mealImg = view.findViewById(R.id.imgViewMeal);
        ingredientTxt = view.findViewById(R.id.ingredientTxt);
        detailsTxt = view.findViewById(R.id.descTxt);
        favBtn = view.findViewById(R.id.favIconBtn);
        isFavorite = false ;
        myMeal = mealDetailsFragmentArgs.fromBundle(getArguments()).getMealPojo();
        Log.d("TAG", "onCreateView: >>>>>>>>>>>>>>>>>>>>"+myMeal.getIdMeal());
        localMealPojo = new LocalMealPojo(myMeal.getIdMeal(),myMeal.getStrMeal(),myMeal.getStrMealThumb());
        Log.d("TAG", "onCreateView: >>>>>>>>>>>>>>>>>>>>"+localMealPojo.getMealId());
        Glide.with(getActivity().getApplicationContext())
                .load(myMeal.getStrMealThumb())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(mealImg);
        mealName.setText(myMeal.getStrMeal());
        mealCat.setText(myMeal.getStrCategory());
        mealCountry.setText(myMeal.getStrArea());
        ingredientTxt.setText("Description");
        detailsTxt.setText(myMeal.getStrInstructions());
        presenter = new MealDetailsPresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())),this);
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFavorite){
                    presenter.addToFav(localMealPojo);
                    isFavorite = true ;
                }else{
                    presenter.removeFromFav(localMealPojo);
                    isFavorite = false;
                }
            }
        });



        return view ;
    }

    @Override
    public void showData(LiveData<List<LocalMealPojo>> favMeals) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavClick(LocalMealPojo mealPojo) {

    }

    @Override
    public void onRemoveFavClick(LocalMealPojo mealPojo) {

    }

    @Override
    public void onCalenderClick(LocalMealPojo mealPojo) {

    }

    @Override
    public void onRemoveCalendereClick(LocalMealPojo mealPojo) {

    }
}