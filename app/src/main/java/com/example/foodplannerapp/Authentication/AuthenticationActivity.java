package com.example.foodplannerapp.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.foodplannerapp.R;

public class AuthenticationActivity extends AppCompatActivity {

NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Log.d("TAG", "onCreate:Auth ");
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);

    }
}