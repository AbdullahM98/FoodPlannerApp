package com.example.foodplannerapp.filter.ByArea.view;

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
import com.example.foodplannerapp.filter.ByArea.presenter.ByAreaPresenter;
import com.example.foodplannerapp.model.AreaPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class FilterByAreaFragment extends Fragment implements FilterByAreaView , onItemClickListener{

    TextView areaTxt;
    RecyclerView areaRecyView;
    LinearLayoutManager manager ;
    MealsByAreaAdapter adapter ;
    AreaPojo areaPojo ;
    ByAreaPresenter presenter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        areaPojo = FilterByAreaFragmentArgs.fromBundle(getArguments()).getAreaPojo();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_by_area, container, false);
        areaTxt = view.findViewById(R.id.areaTitleTxt);
        areaRecyView = view.findViewById(R.id.recyViewArea);
        manager = new LinearLayoutManager(this.getContext());
        adapter = new MealsByAreaAdapter(this.getContext(),new ArrayList<>(),this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        areaRecyView.setLayoutManager(manager);
        areaRecyView.setAdapter(adapter);
        areaTxt.setText("Filter By " +areaPojo.getStrArea());
        presenter = new ByAreaPresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(this.getContext())),this);
        presenter.getByArea(areaPojo.getStrArea());
        return view ;
    }

    @Override
    public void updateList(List<MealPojo> mealsList) {
        adapter.setList(mealsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(String mealId) {

    }
}