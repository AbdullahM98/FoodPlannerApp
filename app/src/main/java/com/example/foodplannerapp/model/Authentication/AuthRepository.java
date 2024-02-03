package com.example.foodplannerapp.model.Authentication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.view.Authentication.Presenter.IAuthResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthRepository implements IAuthRepo {
    FirebaseAuth fireAuth;
    ;

    private static AuthRepository authRepository;
    private AuthRepository(FirebaseAuth fireAuth) {
        this.fireAuth = fireAuth;

    }
    public static AuthRepository getInstance(){
        if(authRepository == null){
            authRepository = new AuthRepository(FirebaseAuth.getInstance());
        }
        return authRepository;
    }
    @Override
    public void registerUser(IAuthResponse authResponse,String email , String password) {
        Log.d("TAG", "registerUser: reached repo ");
        fireAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                           // FirebaseUser user = fireAuth.getCurrentUser();
                            authResponse.onSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            authResponse.onFailure();
                            //updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void login(IAuthResponse iAuthResponse,String email, String password) {
        fireAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    iAuthResponse.onSuccess();
                    Log.d("TAG", "signIn succesful: ");
                }else{
                    iAuthResponse.onFailure();
                    Log.d("TAG", "signIn failed: ");
                }
            }
        });
    }

}
