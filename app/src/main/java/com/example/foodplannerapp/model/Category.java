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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
