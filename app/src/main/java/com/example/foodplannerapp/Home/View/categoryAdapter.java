package com.example.foodplannerapp.Home.View;

import android.content.Context;
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

import java.util.List;


public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder>  {
    List<Category> categories;
    Category category ;
    Context context;
    ViewHolder holder;
    onCategoryClickListener listener ;

    public categoryAdapter(Context context, List<Category> categories ) {
        this.categories = categories;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_row,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        category = categories.get(position);
        holder.titleTxt.setText(category.getName());

        Glide.with(context)
                .load(category.getImgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgView);
        holder.category_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = categories.get(position);
                listener.onClick(category.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void setList(List<Category> categories){
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView category_row ;
        ImageView imgView ;
        TextView titleTxt;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_row = itemView.findViewById(R.id.category_row);
            titleTxt = itemView.findViewById(R.id.textViewHomeMealName);
            imgView = itemView.findViewById(R.id.imageViewHomeCategory);




        }
    }
}
