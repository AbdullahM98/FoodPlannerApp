package com.example.foodplannerapp.view.Category.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.RemoteData.Repository;
import com.example.foodplannerapp.view.Category.presenter.IPresenter;
import com.example.foodplannerapp.view.Category.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements CategoryViewInterface {
    List<Category> categories ;
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    CategoryAdapter adapetr ;

    IPresenter presenter ;



    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        // Inflate the layout for this fragment
        categories = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyView);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapetr = new CategoryAdapter(this.getContext(),categories);
        recyclerView.setAdapter(adapetr);
        presenter = new Presenter(this, Repository.getInstance(RemoteDataSource.getInstance()));
        presenter.getData();

        return view;
    }


    @Override
    public <T>void showData(List<T> categories) {
        adapetr.setList((List<Category>) categories);
        adapetr.notifyDataSetChanged();

    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }
}