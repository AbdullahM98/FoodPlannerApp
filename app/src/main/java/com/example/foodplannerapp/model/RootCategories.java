package com.example.foodplannerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RootCategories implements Serializable {


    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    public RootCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
