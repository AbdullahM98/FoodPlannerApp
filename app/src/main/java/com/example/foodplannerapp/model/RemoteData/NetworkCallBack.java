package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.Category;

import java.util.List;

public interface NetworkCallBack {
   void onSuccessResult(List<Category> categories);
   void onFailedResult(String errorMsg);
}
