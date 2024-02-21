package com.example.foodplannerapp.onBoarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplannerapp.R;


public class OnBoardingFragment2 extends Fragment {

    Button nextBtn ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding2, container, false);
        nextBtn = view.findViewById(R.id.nxtBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(nextBtn).navigate(R.id.action_onBoardingFragment2_to_onBoardingFragment3);
            }
        });
        return view;
    }
}