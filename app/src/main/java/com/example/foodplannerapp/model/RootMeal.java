package com.example.foodplannerapp.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class RootMeal {

	@SerializedName("meals")
	private ArrayList<MealPojo> meals;

	public ArrayList<MealPojo> getMeals(){
		return meals;
	}
}