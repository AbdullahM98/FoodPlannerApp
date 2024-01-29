package com.example.foodplannerapp.view.Category.View;

import com.example.foodplannerapp.model.Category;

import java.util.List;

public interface CategoryViewInterface {
        public <T>void showData(List<T> categories);
        void onFailure(String error);
}
