package com.example.foodplannerapp.view.Category.presenter;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.RemoteData.NetworkCallBack;
import com.example.foodplannerapp.model.RemoteData.RepoInterface;
import com.example.foodplannerapp.view.Category.View.CategoryViewInterface;

import java.util.List;

public class Presenter implements NetworkCallBack , IPresenter{
    CategoryViewInterface   categoryView;
    RepoInterface repo ;

    public Presenter(CategoryViewInterface categoryView , RepoInterface repo) {

        this.repo = repo;
        this.categoryView = categoryView;

    }

    @Override
    public void onSuccessResult(List<Category> categories) {
        categoryView.showData(categories);
    }

    @Override
    public void onFailedResult(String errorMsg) {
        categoryView.onFailure(errorMsg);
    }

    @Override
    public void getData() {
        repo.getAllCategories(this);
    }
}
