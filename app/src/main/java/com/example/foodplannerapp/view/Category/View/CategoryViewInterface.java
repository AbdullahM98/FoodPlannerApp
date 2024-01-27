package com.example.foodplannerapp.view.Category.View;

import com.example.foodplannerapp.model.Category;

import java.util.List;

public interface CategoryViewInterface {
        public void showData(List<Category> categories);
        void onFailure(String error);
}
