package com.example.foodplannerapp.search.view;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;
import com.example.foodplannerapp.search.presenter.ISearchPresenter;
import com.example.foodplannerapp.search.presenter.SearchPresenterImp;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment implements ISearchView, OnItemClickListener {

    SearchAdapter adapter;
    LinearLayoutManager manager ;
    List<MealPojo> searchResults ;
    RecyclerView searchRecyView ;
    ISearchPresenter presenter;
    SearchView searchView ;
    Chip categoryFilterChip , ingFilterChip , countryFilterChip;
    BottomSheetDialog bottomSheetDialog;
    Button filterBtn;
    List<String> filterCountries ;
    List<String> filterCategories ;
    List<String> filterIngredients ;

    LottieAnimationView animationView;
    MealPojo mealPojo;
    String  checkedBtnText ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        filterCategories = new ArrayList<>(Arrays.asList("Beef","Chicken","Dessert","Lamb","Miscellaneous","Pasta","Pork","Seafood","Side","Starter"));
        filterIngredients = new ArrayList<>(Arrays.asList("Chicken","Olive Oil","Greek Yogurt","Garlic Clove","Cumin","Lettuce","Tomato","Pita Bread","Butter","Salt"));
        filterCountries = new ArrayList<>(Arrays.asList("Egyptian","Canadian","British","French","Tunisian","Indian","Irish","Japanese","Croatian","Italian"));
        searchResults = new ArrayList<>();
        mealPojo = new MealPojo();
        adapter = new SearchAdapter(this.getContext(),searchResults,this);


        animationView = view.findViewById(R.id.animation_view);
        manager = new LinearLayoutManager(this.getContext());
        searchRecyView = view.findViewById(R.id.searchRecyView);
        searchView = view.findViewById(R.id.searchView);
        countryFilterChip = view.findViewById(R.id.countryFilterChip);
        categoryFilterChip = view.findViewById(R.id.categoryFilterChip);
        ingFilterChip = view.findViewById(R.id.ingredientFilterChip);
        bottomSheetDialog = new BottomSheetDialog(this.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecyView.setLayoutManager(manager);
        searchRecyView.setAdapter(adapter);

        presenter = new SearchPresenterImp(this, Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(this.getContext())));


        bottomSheetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        searchView.clearFocus();
        if(adapter.getMeals().size()==0){
            animationView.setVisibility(View.VISIBLE);
            searchRecyView.setVisibility(View.GONE);
        }else{
            animationView.setVisibility(View.INVISIBLE);
            searchRecyView.setVisibility(View.VISIBLE);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                presenter.searchMealByName(newText);
                return true;
            }
        });

        countryFilterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomDialog(filterCountries,2);
                bottomSheetDialog.show();
            }
        });
        categoryFilterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomDialog(filterCategories,1);
                bottomSheetDialog.show();
            }
        });
        ingFilterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomDialog(filterIngredients,3);
                bottomSheetDialog.show();
            }
        });
//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                presenter.searchMealByName(editable.toString());
//            }
//        });


        // Inflate the layout for this fragment
        return view ;
    }

    @Override
    public void updateList(List<MealPojo> searchResultMeals) {
        if(searchResultMeals!=null){
        adapter.setList(searchResultMeals);
        adapter.notifyDataSetChanged();
        }
        animationView.setVisibility(View.INVISIBLE);
        searchRecyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateSingleMeal(MealPojo mealPojoo) {
        Log.d("TAGG", "updateSingleMeal: "+mealPojoo.getIdMeal());
        SearchFragmentDirections.ActionSearchFragment2ToMealDetailsFragment action = SearchFragmentDirections.actionSearchFragment2ToMealDetailsFragment(mealPojoo);
        Navigation.findNavController(searchView).navigate(action);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void createBottomDialog(List<String> items , int mode){
        View view = getLayoutInflater().inflate(R.layout.filter_bottom_dialog,null,false);
        filterBtn = view.findViewById(R.id.filterBtn);

        RadioGroup radioGroup = view.findViewById(R.id.filterRadioGroup);
        for (int i =  0; i < radioGroup.getChildCount(); i++) {
            View childView = radioGroup.getChildAt(i);
            if (childView instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childView;
                radioButton.setText(items.get(i));
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        checkedBtnText = radioButton.getText().toString();
                        Log.d("TAG", "onCheckedChanged: "+checkedBtnText);
                    }
                });


            }
        }
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(radioGroup.getCheckedRadioButtonId()==-1){
                 Toast.makeText(getActivity().getApplicationContext(), "No Selected Filter", Toast.LENGTH_SHORT).show();
                 bottomSheetDialog.dismiss();

             }else{
                 int selectedId = radioGroup.getCheckedRadioButtonId();
               //  RadioButton selectedRadioButton = view.findViewById(checkedBtnId);

                 // Get the text of the checked radio button
              //   String selectedText = selectedRadioButton.getText().toString();
                 switch (mode){
                     case 1:
                      //   Toast.makeText(getActivity().getApplicationContext(), ""+selectedText, Toast.LENGTH_SHORT).show();
                         presenter.filterMealByCategory(checkedBtnText);
                         bottomSheetDialog.dismiss();
                         break ;
                     case 2 :
                        presenter.filterMealByCountry(checkedBtnText);
                         bottomSheetDialog.dismiss();
                         break;
                     case 3 :
                         presenter.filterMealByIngredient(checkedBtnText);
                         bottomSheetDialog.dismiss();
                         break;
                     default:
                         Toast.makeText(getActivity().getApplicationContext(), "No Command", Toast.LENGTH_SHORT).show();
                 }
             }

            }
        });
        bottomSheetDialog.setContentView(view);
    }


    @Override
    public void getMealById(String mealId) {
        Log.d("TAGG", "yaPresenter getMealById: "+mealId);
        presenter.getMealById(mealId);
    }
}