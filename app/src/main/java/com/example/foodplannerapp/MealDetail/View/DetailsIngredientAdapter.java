package com.example.foodplannerapp.MealDetail.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.filter.ByArea.view.onItemClickListener;

import java.util.List;




public class DetailsIngredientAdapter extends RecyclerView.Adapter<DetailsIngredientAdapter.ViewHolder> {
    List<String> ingredients;
    String currIng ;
    Context context;
    ViewHolder holder;


    public DetailsIngredientAdapter(Context context, List<String> ingredients ) {
        this.ingredients = ingredients;
        this.context = context;

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
        Log.d("TAGG", "onBindViewHolder: "+position);
        currIng = ingredients.get(position);



            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/"+currIng+"-Small.png")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView1);




        }







    @Override
    public int getItemCount() {
        return ingredients.size();
    }
    public void setList(List<String> ingredients){
        this.ingredients = ingredients;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView1 ;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 =itemView.findViewById(R.id.imgVwIng);






        }

    }
}
