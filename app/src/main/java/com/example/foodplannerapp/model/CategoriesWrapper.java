package com.example.foodplannerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesWrapper {
    public List<Category> getCategories() {
        return categories;
    }

    @SerializedName("categories")
    @Expose
    private List<Category> categories;
}
