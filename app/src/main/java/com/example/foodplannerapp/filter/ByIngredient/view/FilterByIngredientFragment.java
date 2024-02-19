package com.example.foodplannerapp.filter.ByIngredient.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.filter.ByArea.view.onItemClickListener;
import com.example.foodplannerapp.filter.ByIngredient.presenter.ByIngredientPresenter;
import com.example.foodplannerapp.model.IngredientPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class FilterByIngredientFragment extends Fragment implements IFilterByIngredientView , onItemClickListener {
        TextView ingTitleTxt;
        RecyclerView ingRecyView;
        LinearLayoutManager manager ;
        MealsByIngredientAdapter adapter;
        ByIngredientPresenter presenter ;
        IngredientPojo ingPojo;

    public FilterByIngredientFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingPojo = FilterByIngredientFragmentArgs.fromBundle(getArguments()).getIngredientPojo();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_by_ingredient, container, false);
        ingTitleTxt = view.findViewById(R.id.ingTitleTxt);
        ingRecyView = view.findViewById(R.id.recyViewIng);
        manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter= new MealsByIngredientAdapter(this.getContext() , new ArrayList<>(),this);
        ingRecyView.setAdapter(adapter);
        ingRecyView.setLayoutManager(manager);
        presenter = new ByIngredientPresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(this.getContext())),this);
        ingTitleTxt.setText("FilterBy "+ingPojo.getStrIngredient());
        presenter.getByIngredient(ingPojo.getStrIngredient());

        return view;
    }

    @Override
    public void updateList(List<MealPojo> mealsList) {
        adapter.setList(mealsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this.getContext(), "Couldn't get Meals , check your network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(String mealId) {

    }
}