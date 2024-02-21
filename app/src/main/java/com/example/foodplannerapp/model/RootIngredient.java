package com.example.foodplannerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootIngredient {
    @SerializedName("meals")
    private List<IngredientPojo> ingredientPojos;

    public List<IngredientPojo> getIngredientPojos() {
        return ingredientPojos;
    }

    public void setIngredientPojos(List<IngredientPojo> ingredientPojos) {
        this.ingredientPojos = ingredientPojos;
    }
}
