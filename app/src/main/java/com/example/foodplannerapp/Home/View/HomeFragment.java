package com.example.foodplannerapp.Home.View;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.foodplannerapp.Home.presenter.Presenter;
import com.example.foodplannerapp.R;

import com.example.foodplannerapp.model.AreaPojo;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.IngredientPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;
import com.example.foodplannerapp.model.RootCategories;
import com.example.foodplannerapp.home.View.HomeViewInterface;
import com.example.foodplannerapp.Home.presenter.IPresenter;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeFragment extends Fragment implements HomeViewInterface ,onCategoryClickListener,onAreaClickListener , onIngredientClickListener{
    List<Category> categories ;
    List<IngredientPojo> ingredientPojos;
    List<AreaPojo> areaPojos ;
    RecyclerView categoryRecyclerView ,countryRecyclerView , ingRecyView;
    IngredientAdapter ingAdapter;
    CountryAdapter countryAdapter;
    LinearLayoutManager layoutManager , countryLayoutManager , ingLayoutManager ;
    HomeAdapter adapetr ;

    IPresenter presenter ;
    CardView mealCard;
    ImageView mealImg;
    MealPojo mealPojo;
    String userID ;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        Log.d("TAG", "onCreateView: ");

        categories = new ArrayList<>();
        ingredientPojos = new ArrayList<>();
        areaPojos = new ArrayList<>();
        mealCard = view.findViewById(R.id.mealCard);
        mealImg= view.findViewById(R.id.mealImg);
        categoryRecyclerView = view.findViewById(R.id.recyViewCategories);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        ingAdapter = new IngredientAdapter(this.getContext(),ingredientPojos,this);
        adapetr = new HomeAdapter(this.getContext(),categories , this);
        countryAdapter = new CountryAdapter(this.getContext(),areaPojos,this);
        ingRecyView = view.findViewById(R.id.recyViewIng);
        countryLayoutManager = new LinearLayoutManager(this.getContext());
        ingLayoutManager = new LinearLayoutManager(this.getContext());
        ingLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        countryRecyclerView= view.findViewById(R.id.recyViewCountry);
        countryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ingRecyView.setLayoutManager(ingLayoutManager);
        ingRecyView.setAdapter(ingAdapter);
        countryRecyclerView.setAdapter(countryAdapter);
        countryRecyclerView.setLayoutManager(countryLayoutManager);
        categoryRecyclerView.setAdapter(adapetr);
        presenter = new Presenter(this, Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())),Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())));
        presenter.getData();
        presenter.getMealsByCountry();
        presenter.getIngredients();
        presenter.getRandomMeal();
        mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToMealDetailsFragment action = HomeFragmentDirections.actionHomeFragment2ToMealDetailsFragment(mealPojo);
              //  action.setMealPojo(mealPojo);
                Navigation.findNavController(view).navigate(action);
            }
        });
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("key");
            Log.d("TAGG", "onCreateView: "+userID);
            //The key argument here must match that used in the other activity
        }
        return view;
    }


    @Override
    public void showData(List<Category> listOfdata) {
            categories = listOfdata;
            adapetr.setList( listOfdata);
            adapetr.notifyDataSetChanged();



   }

    @Override
    public void showMeal(List<MealPojo> listOfdata) {
                   mealPojo =  listOfdata.get(0);
        Observable.just(mealPojo.getStrMealThumb())
                .flatMap(url -> Observable.create(emitter -> {
                    Bitmap bitmap = null;
                    try {
                        bitmap = Glide.with(getActivity().getApplicationContext())
                                .asBitmap()
                                .load(url)
                                .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get();
                        emitter.onNext(bitmap);
                        emitter.onComplete();
                    } catch (InterruptedException | ExecutionException e) {
                        emitter.onError(e);
                    }
                }))
                .subscribeOn(Schedulers.io()) // Run the operation on a background thread
                .observeOn(AndroidSchedulers.mainThread()) // Switch back to the main thread for UI updates
                .subscribe(item->{
                    mealImg.setImageBitmap((Bitmap) item);
                },throwable -> {
                    Toast.makeText(this.getContext(), "Couldnt get image", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void showCountry(List<AreaPojo> areaPojos) {
        this.areaPojos = areaPojos;
        countryAdapter.setList(areaPojos);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIng(List<IngredientPojo> ingredientPojos) {
        this.ingredientPojos = ingredientPojos;
        ingAdapter.setList(ingredientPojos);
        ingAdapter.notifyDataSetChanged();
    }


    @Override
    public void onFailure(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(String catId) {
        RootCategories rootCategories = new RootCategories(categories);
        HomeFragmentDirections.ActionHomeFragment2ToCategoryFragment action = HomeFragmentDirections.actionHomeFragment2ToCategoryFragment(rootCategories);
        Navigation.findNavController(categoryRecyclerView).navigate(action);
    }

    @Override
    public void onAreaClick(AreaPojo areaPojo) {
        HomeFragmentDirections.ActionHomeFragment2ToFilterByAreaFragment action = HomeFragmentDirections.actionHomeFragment2ToFilterByAreaFragment(areaPojo);
        Navigation.findNavController(countryRecyclerView).navigate(action);

    }

    @Override
    public void onIngredientClick(IngredientPojo ingredientPojo) {
        HomeFragmentDirections.ActionHomeFragment2ToFilterByIngredientFragment action = HomeFragmentDirections.actionHomeFragment2ToFilterByIngredientFragment(ingredientPojo);
        Navigation.findNavController(ingRecyView).navigate(action);
    }
}