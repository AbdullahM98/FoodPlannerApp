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
import com.example.foodplannerapp.model.AreaPojo;
import com.example.foodplannerapp.model.Category;

import java.util.ArrayList;
import java.util.List;




public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>  {
    List<AreaPojo> areaPojoList;
    AreaPojo area ;
    Context context;
    ViewHolder holder;
    onAreaClickListener listener ;

    public CountryAdapter(Context context, List<AreaPojo> areaPojoList , onAreaClickListener onAreaClickListener) {
        this.areaPojoList = areaPojoList;
        this.context = context;
        this.listener = onAreaClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.area_row,parent,false);

        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        area = areaPojoList.get(position);

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                area = areaPojoList.get(position);
                listener.onAreaClick(area);
            }
        });
        Glide.with(context)
                .load(area.getThumbnail())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgView);

    }

    @Override
    public int getItemCount() {
        return areaPojoList.size();
    }
    public void setList(List<AreaPojo> areaPojos){
        this.areaPojoList = areaPojos;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView ;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.areaImg);




        }
    }
}
