package com.example.foodplannerapp.MealDetail.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.CalenderRepo;
import com.example.foodplannerapp.model.Repositories.FavoriteRepo;
import com.example.foodplannerapp.model.Repositories.Repository;
import com.example.foodplannerapp.MealDetail.Presenter.IMealsPresenter;
import com.example.foodplannerapp.MealDetail.Presenter.MealDetailsPresenter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class mealDetailsFragment extends Fragment implements IMealDetailsView , OnClickListener {

    private IMealsPresenter presenter ;
    private TextView mealName , mealCat , mealCountry , ingredientTxt , detailsTxt ;
    private  ImageView mealImg , favBtn , calenderBtn;
    private  MealPojo myMeal;
    private RecyclerView ingRecyView;
    private LinearLayoutManager manager ;

    private DetailsIngredientAdapter ingredientAdapter ;

    private LocalMealPojo localMealPojo;

    private boolean isFavorite , isCalenderVisible ;
    private CalendarView calender;
    private String dateSelectedStr;
    YouTubePlayerView youTubePlayerView;
  //  private WebView webView ;

    public mealDetailsFragment() {
        // Required empty public constructors
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_meal_detail, container, false);
        ingRecyView = view.findViewById(R.id.ingRecyView);
        ingredientAdapter = new DetailsIngredientAdapter(this.getContext(),new ArrayList<>());
        manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ingRecyView.setLayoutManager(manager);
        ingRecyView.setAdapter(ingredientAdapter);
        youTubePlayerView = view.findViewById(R.id.youTubePlayerView);
        mealName = view.findViewById(R.id.mealNameTxtView);
        mealCat = view.findViewById(R.id.mealCategoryTxtView);
        mealCountry = view.findViewById(R.id.countryTxtView);
        mealImg = view.findViewById(R.id.imgViewMeal);
        ingredientTxt = view.findViewById(R.id.receipeTitleTxt);
        detailsTxt = view.findViewById(R.id.descTxt);
        favBtn = view.findViewById(R.id.favIconBtn);
        calenderBtn = view.findViewById(R.id.calenderIconBtn);
        calender =view.findViewById(R.id.calendarView);
       // webView = view.findViewById(R.id.webView);

        calender.setVisibility(View.INVISIBLE);
        isFavorite = false ;
        isCalenderVisible = false;
        myMeal = mealDetailsFragmentArgs.fromBundle(getArguments()).getMealPojo();
        localMealPojo = new LocalMealPojo(myMeal.getIdMeal(),myMeal.getStrMeal(),myMeal.getStrMealThumb());
        Log.d("TAG", "onCreateView: >>>>>>>>>>>>>>>>>>>>"+localMealPojo.getMealId());
        Glide.with(getActivity().getApplicationContext())
                .load(myMeal.getStrMealThumb())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(mealImg);
        mealName.setText(myMeal.getStrMeal());
        getIngredients();
        mealCat.setText(myMeal.getStrCategory());
        mealCountry.setText(myMeal.getStrArea());
        ingredientTxt.setText("Description");
        detailsTxt.setText(myMeal.getStrInstructions());
        presenter = new MealDetailsPresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(getActivity().getApplicationContext())),this, CalenderRepo.getInstance(), FavoriteRepo.getInstance());
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFavorite){
                    presenter.addToFav(myMeal);
                    favBtn.setImageResource(R.drawable.reshot_icon_heart);
                    isFavorite = true ;
                }else{
                    presenter.removeFromFav(myMeal);
                    favBtn.setImageResource(R.drawable.heart);

                    isFavorite = false;
                }
            }
        });

        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!isCalenderVisible){
                   isCalenderVisible = true;
                   calender.setVisibility(View.VISIBLE);
               }else {
                   isCalenderVisible = false;
                   calender.setVisibility(View.INVISIBLE);
               }
            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateSelectedStr = Integer.toString(i)+Integer.toString(i1+1)+Integer.toString(i2);
                presenter.addMealToCalender(dateSelectedStr,myMeal);
            }
        });
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String videoMealDetail = getVideoLink(myMeal.getStrYoutube());
                youTubePlayer.cueVideo(videoMealDetail, 0);
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
    private void getIngredients(){
        List<String> ingredients = new ArrayList<>();
        ingredients.add(myMeal.getStrIngredient1());
        ingredients.add(myMeal.getStrIngredient2());
        ingredients.add(myMeal.getStrIngredient3());
        ingredients.add(myMeal.getStrIngredient4());
        ingredients.add(myMeal.getStrIngredient5());
        ingredients.add(myMeal.getStrIngredient6());
        ingredientAdapter.setList(ingredients);
        ingredientAdapter.notifyDataSetChanged();


    }
    public String getVideoLink(String link) {
        if (link != null && link.split("\\?v=").length > 1)
            return link.split("\\?v=")[1];
        else return "";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        youTubePlayerView.release();
    }
}