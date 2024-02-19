package com.example.foodplannerapp.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.IngredientPojo;

import java.util.List;







public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>  {
    List<IngredientPojo> ingredientPojos;
    IngredientPojo ingredientPojo ;
    Context context;
    ViewHolder holder;
    onIngredientClickListener listener ;

    public IngredientAdapter(Context context, List<IngredientPojo> ingredientPojos, onIngredientClickListener onIngredientClickListener) {
        this.ingredientPojos = ingredientPojos;
        this.context = context;
        this.listener = onIngredientClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient_row,parent,false);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ingredientPojo = ingredientPojos.get(position);
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientPojo = ingredientPojos.get(position);
                listener.onIngredientClick(ingredientPojo);
            }
        });
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/"+ingredientPojo.getStrIngredient()+"-Small.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgView);

    }

    @Override
    public int getItemCount() {
        return ingredientPojos.size();
    }
    public void setList(List<IngredientPojo> ingredientPojos){
        this.ingredientPojos = ingredientPojos;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView ;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgVwIng);




        }
    }
}
