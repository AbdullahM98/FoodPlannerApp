package com.example.foodplannerapp.view.Home.Favorite.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.LocalDataSource.LocalMealPojo;
import com.example.foodplannerapp.model.MealPojo;

import java.util.List;



public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    List<LocalMealPojo> meals;
    LocalMealPojo myMeal ;
    Context context;

    ViewHolder holder ;


    public FavAdapter(Context context, List<LocalMealPojo> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fav_row,parent,false);
        holder = new ViewHolder(view);

        Log.d("TAG", "onCreateViewHolder: "+holder.toString());
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        myMeal = meals.get(position);
        holder.titleTxt.setText(myMeal.getMealName());
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meals.remove(position);
                notifyDataSetChanged();
            }
        });
        Glide.with(context)
                .load(myMeal.getImgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setList(List<LocalMealPojo> meals){
        this.meals = meals;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView fav_row ;
        ImageView imgView , removeBtn;
        TextView titleTxt;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fav_row = itemView.findViewById(R.id.favRow);
            titleTxt = itemView.findViewById(R.id.textViewMealName);
            imgView = itemView.findViewById(R.id.imageViewFav);
            removeBtn = itemView.findViewById(R.id.removeImgView);




        }
    }
}
