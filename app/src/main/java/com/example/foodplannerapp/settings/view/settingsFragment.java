package com.example.foodplannerapp.settings.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplannerapp.Authentication.AuthenticationActivity;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Authentication.AuthRepository;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;
import com.example.foodplannerapp.settings.presenter.ISettingsPresenter;
import com.example.foodplannerapp.settings.presenter.SettingsPresenter;
import com.example.foodplannerapp.view.MainActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsFragment extends Fragment {
ISettingsPresenter presenter;
    List<LocalMealPojo> meals;
    RecyclerView recyView;
    LinearLayoutManager layoutManager;
  Intent intent;
Button logoutBtn;
    public settingsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        logoutBtn = view.findViewById(R.id.logoutBtn);

        presenter = new SettingsPresenter(AuthRepository.getInstance(), Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(this.getContext())));
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), AuthenticationActivity.class);
                    presenter.logOut();
                startActivity(intent);
            }
        });
        return view;
    }
}