package com.example.foodplannerapp.filter.ByArea.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.search.view.OnItemClickListener;

import java.util.List;




public class MealsByAreaAdapter extends RecyclerView.Adapter<MealsByAreaAdapter.ViewHolder> {
    List<MealPojo> meals;
    MealPojo mealPojo ;
    Context context;
    ViewHolder holder;
    onItemClickListener listener;

    public MealsByAreaAdapter(Context context, List<MealPojo> meals ,onItemClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.filter_by_row,parent,false);
        holder = new MealsByAreaAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position < meals.size()) {

            MealPojo mealPojo = meals.get(position);
            Log.d("TAGG", "onBindViewHolder: first"+meals.indexOf(mealPojo) );
            holder.titleTxt1.setText(mealPojo.getStrMeal());
            Glide.with(context)
                    .load(mealPojo.getStrMealThumb())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView1);
           mealPojo = meals.get(meals.indexOf(mealPojo)+1);
            // Check if there is a next meal in the list
            if (position +  1 < meals.size()) {
                MealPojo nextMealPojo = meals.get(meals.indexOf(mealPojo)+1);
                Log.d("TAGG", "onBindViewHolder: second"+meals.indexOf(nextMealPojo));

                holder.titleTxt2.setText(nextMealPojo.getStrMeal());
                Glide.with(context)
                        .load(nextMealPojo.getStrMealThumb())
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(holder.imageView2);
            }
        }




    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setList(List<MealPojo> meals){
        this.meals = meals;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView1 ,imageView2;
        TextView titleTxt1 , titleTxt2;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 =itemView.findViewById(R.id.imageView1);
            titleTxt1 = itemView.findViewById(R.id.textView1);
            imageView2 =itemView.findViewById(R.id.imageView2);
            titleTxt2 = itemView.findViewById(R.id.textView2);





        }

    }
}
