package com.example.foodplannerapp.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodplannerapp.R;

import com.example.foodplannerapp.model.MealPojo;


import java.util.List;
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
        List<MealPojo> meals;
        MealPojo mealPojo ;
        Context context;
        ViewHolder holder;
        OnItemClickListener listener;

        public SearchAdapter(Context context, List<MealPojo> meals ,OnItemClickListener listener) {
            this.meals = meals;
            this.context = context;
            this.listener = listener;
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
            Log.d("TAGG", "onBindViewHolder: "+position);
            mealPojo = meals.get(position);
            holder.searchTxt.setText(mealPojo.getStrMeal());
            holder.searchItem.startAnimation(AnimationUtils.loadAnimation(holder.searchItem.getContext(),R.anim.anim));
            holder.searchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mealPojo = meals.get(position);
                    Log.d("TAGG", "from adapter id is: "+position);

                    listener.getMealById(mealPojo.getIdMeal());
                    Log.d("TAG", "onItemClick: ");

                }
            });
        }

        @Override
        public int getItemCount() {
            return meals.size();
        }
        public void setList(List<MealPojo> meals){
            this.meals = meals;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            ConstraintLayout searchItem;
            TextView searchTxt;




            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                searchTxt = itemView.findViewById(R.id.searchResult);
                searchItem = itemView.findViewById(R.id.search_row);





            }

        }
    }
