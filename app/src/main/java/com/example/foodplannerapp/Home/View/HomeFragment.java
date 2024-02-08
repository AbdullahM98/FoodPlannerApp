package com.example.foodplannerapp.Home.View;

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

import com.example.foodplannerapp.R;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;
import com.example.foodplannerapp.view.Communicator;
import com.example.foodplannerapp.home.View.HomeViewInterface;
import com.example.foodplannerapp.home.View.HomeAdapter;
import com.example.foodplannerapp.home.presenter.IPresenter;
import com.example.foodplannerapp.Home.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface {
    List<Category> categories ;
    RecyclerView recyclerView;
    Communicator communicator;
    LinearLayoutManager layoutManager;
    HomeAdapter adapetr ;

    IPresenter presenter ;
    CardView mealCard;
    ImageView mealImg;
    MealPojo mealPojo;

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
        mealCard = view.findViewById(R.id.mealCard);
        mealImg= view.findViewById(R.id.mealImg);
        recyclerView = view.findViewById(R.id.recyViewCategories);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapetr = new HomeAdapter(this.getContext(),categories);
        recyclerView.setAdapter(adapetr);
        presenter = new Presenter(this, Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())));
        presenter.getData();
        mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToMealDetailsFragment action = HomeFragmentDirections.actionHomeFragment2ToMealDetailsFragment(mealPojo);
              //  action.setMealPojo(mealPojo);
                Navigation.findNavController(view).navigate(action);
            }
        });

        return view;
    }


    @Override
    public void showData(List<Category> listOfdata) {

            adapetr.setList( listOfdata);
            adapetr.notifyDataSetChanged();



   }

    @Override
    public void showMeal(List<MealPojo> listOfdata) {
                   mealPojo =  listOfdata.get(0);
//           Glide.with(getActivity().getApplicationContext())
//                   .load(mealPojo.getStrMealThumb())
//                  .centerCrop()
//                    .placeholder(R.drawable.ic_launcher_foreground)
//                  .into(mealImg);
    }


    @Override
    public void onFailure(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }
}