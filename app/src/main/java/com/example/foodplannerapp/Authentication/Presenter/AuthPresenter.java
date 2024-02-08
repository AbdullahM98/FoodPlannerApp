package com.example.foodplannerapp.Authentication.Presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.model.Authentication.IAuthRepo;
import com.example.foodplannerapp.view.Authentication.IAuthView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.foodplannerapp.view.Authentication.Presenter.onClickListener;
import com.example.foodplannerapp.view.Authentication.Presenter.IAuthResponse;
import java.util.concurrent.Executor;

public class AuthPresenter implements onClickListener, IAuthResponse {

    IAuthRepo authRepo ;
     IAuthView iAuthView;
    public AuthPresenter(IAuthRepo authRepo , IAuthView iAuthView) {
        this.authRepo = authRepo;
        this.iAuthView = iAuthView;
    }

    @Override
    public void onSignInClick(String email, String pass) {
        authRepo.login(this,email,pass);
    }

    @Override
    public void onRegisterClick(String email ,String password) {
        authRepo.registerUser(this,email,password);
        Log.d("TAG", "onClick: fired from presenter");
    }

    @Override
    public void onSuccess() {
        iAuthView.updateUi();
    }

    @Override
    public void onFailure() {
        iAuthView.showToast("Failed");
    }
}
