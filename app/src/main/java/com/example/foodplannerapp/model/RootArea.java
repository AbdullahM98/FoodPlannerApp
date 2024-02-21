package com.example.foodplannerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootArea {
    @SerializedName("meals")
    List<AreaPojo> areas ;

    public List<AreaPojo> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaPojo> areas) {
        this.areas = areas;
    }
}
