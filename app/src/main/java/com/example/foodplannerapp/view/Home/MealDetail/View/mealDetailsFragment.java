package com.example.foodplannerapp.view.Home.MealDetail.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.view.Home.MealDetail.Presenter.MealDetailsPresenter;
import com.example.foodplannerapp.view.Home.home.View.HomeFragment;
import com.example.foodplannerapp.view.Home.home.presenter.Presenter;


public class mealDetailsFragment extends Fragment {
    MealDetailsPresenter presenter ;
    TextView mealName , mealCat , mealCountry , ingredientTxt , detailsTxt ;
    ImageView mealImg ;
    MealPojo myMeal;

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

        myMeal = mealDetailsFragmentArgs.fromBundle(getArguments()).getMealPojo();
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



        return view ;
    }
}