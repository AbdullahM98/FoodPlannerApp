package com.example.foodplannerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.view.Category.View.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoryFragment fragment = new CategoryFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.add(R.id.fragmentContainerView2,fragment,"CategoryFragment");
        Log.d("TAG", "onCreate: Activity");
    }
}