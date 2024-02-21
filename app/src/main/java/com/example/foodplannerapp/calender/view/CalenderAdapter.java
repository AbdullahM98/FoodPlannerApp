package com.example.foodplannerapp.calender.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.LocalCalenderPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.search.view.OnItemClickListener;

import java.util.List;



public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder> {
    List<LocalCalenderPojo> plans;
    LocalCalenderPojo planPojo ;
    Context context;
    ViewHolder holder;
    onRemoveFromCalender listener ;

    public CalenderAdapter(Context context, List<LocalCalenderPojo> plans  ,onRemoveFromCalender listener) {
        this.plans = plans;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calender_row,parent,false);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        planPojo = plans.get(position);
        Glide.with(context)
                .load(planPojo.getImgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mealImg);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planPojo = plans.get(position);
                listener.removeFromCalender(planPojo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }
    public void setList(List<LocalCalenderPojo> meals){
        this.plans = meals;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mealCard;
       ImageView deleteBtn , mealImg;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealCard = itemView.findViewById(R.id.calender_row);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            mealImg = itemView.findViewById(R.id.imageViewCalenderMeal);




        }

    }
}
