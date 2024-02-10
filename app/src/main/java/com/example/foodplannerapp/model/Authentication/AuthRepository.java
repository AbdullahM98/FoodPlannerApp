package com.example.foodplannerapp.model.Authentication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.Authentication.Presenter.IAuthResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthRepository implements IAuthRepo {
    FirebaseAuth fireAuth;
    FirebaseUser user ;

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
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                           // FirebaseUser user = fireAuth.getCurrentUser();
                            authResponse.onSuccess(user.getUid());
                            Log.d("Auth", "onComplete: username "+user.getDisplayName());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            authResponse.onFailure("Sign Up failed , Try Again");
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
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    iAuthResponse.onSuccess(user.getUid());
                    Log.d("TAG", "signIn succesful: ");
                }else{
                    iAuthResponse.onFailure("Sign In failed , Try Again");
                    Log.d("TAG", "signIn failed: ");
                }
            }
        });
    }

}
