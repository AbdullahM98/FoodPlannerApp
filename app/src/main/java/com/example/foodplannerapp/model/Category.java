package com.example.foodplannerapp.model;

import com.google.gson.annotations.SerializedName;


public class Category {
    @SerializedName("idCategory")
    private String id;
    @SerializedName("strCategory")
    private String name;

    @SerializedName("strCategoryThumb")
    private String imgUrl;

    @SerializedName("strCategoryDescription")
    private String description;

    public Category(String id, String name, String imgUrl, String description) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
    }
}
