package com.example.foodplannerapp.view.Authentication.Presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.view.Authentication.IAuthView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthPresenter implements onClickListener {
     FirebaseAuth fireAuth;
     IAuthView iAuthView;
    public AuthPresenter(FirebaseAuth fireAuth , IAuthView iAuthView) {
        this.fireAuth = fireAuth;
        this.iAuthView = iAuthView;
    }

    @Override
    public void onClick(String email ,String password) {
        fireAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = fireAuth.getCurrentUser();
                           iAuthView.updateUi("signIn");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            iAuthView.showToast("Failed");
                            //updateUI(null);
                        }
                    }
                });
    }
}
