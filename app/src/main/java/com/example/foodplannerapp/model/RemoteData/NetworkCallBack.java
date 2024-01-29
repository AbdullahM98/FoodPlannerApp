package com.example.foodplannerapp.model.RemoteData;

import com.example.foodplannerapp.model.Category;

import java.util.List;

public interface NetworkCallBack {
  <T> void onSuccessResult(List<T> obj);
   void onFailedResult(String errorMsg);
}
