package com.example.foodplannerapp.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodplannerapp.R;

import com.example.foodplannerapp.model.MealPojo;


import java.util.List;
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
        List<MealPojo> meals;
        MealPojo mealPojo ;
        Context context;
        ViewHolder holder;

        public SearchAdapter(Context context, List<MealPojo> meals) {
            this.meals = meals;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.search_row,parent,false);
            holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            mealPojo = meals.get(position);
            holder.searchTxt.setText(mealPojo.getStrMeal());


        }

        @Override
        public int getItemCount() {
            return meals.size();
        }
        public void setList(List<MealPojo> meals){
            this.meals = meals;
        }
        public static class ViewHolder extends RecyclerView.ViewHolder{
            CardView searchItem;
            TextView searchTxt;




            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                searchTxt = itemView.findViewById(R.id.searchResult);
                searchItem = itemView.findViewById(R.id.searchItemCard);
                searchItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });




            }
        }
    }