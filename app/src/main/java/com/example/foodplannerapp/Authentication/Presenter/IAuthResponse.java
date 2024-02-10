package com.example.foodplannerapp.Authentication.Presenter;

public interface IAuthResponse {
    void onSuccess(String userId);
    void onFailure(String error);
}
