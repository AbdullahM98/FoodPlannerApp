package com.example.foodplannerapp.model.Authentication;

import com.example.foodplannerapp.Authentication.Presenter.IAuthResponse;

public interface IAuthRepo {
    void registerUser(IAuthResponse iAuthResponse, String email , String Password);
    void login(IAuthResponse iAuthResponse,String email , String password );
}
