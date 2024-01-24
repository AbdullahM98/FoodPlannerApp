package com.example.foodplannerapp.model;

import java.util.List;

public interface NetworkCallBack {
   void onSuccessResult(List<Category> categories);
   void onFailedResult(String errorMsg);
}
