package com.example.foodplannerapp.Authentication.Presenter;

public interface onClickListener {
    void onSignInClick(String email,String pass);
    void onRegisterClick(String email,String pass , String userName);
}
