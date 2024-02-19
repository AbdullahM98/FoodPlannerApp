package com.example.foodplannerapp.Home.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.RootCategories;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {


categoryAdapter adapter;
LinearLayoutManager manager;
RecyclerView recyclerView;
List<Category> categories;
RootCategories rootCategories;
com.example.foodplannerapp.Home.presenter.IPresenter presenter ;

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
        categories =new ArrayList<>();
        adapter = new categoryAdapter(this.getContext(),categories);
        recyclerView = view.findViewById(R.id.recyView);
        manager  = new LinearLayoutManager(this.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        rootCategories = CategoryFragmentArgs.fromBundle(getArguments()).getCategories();
        adapter.setList(rootCategories.getCategories());
        adapter.notifyDataSetChanged();

        // Inflate the layout for this fragment
        return view;
    }
}