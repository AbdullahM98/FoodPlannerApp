package com.example.foodplannerapp.view.Authentication.Presenter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.view.Authentication.AuthenticationActivity;
import com.example.foodplannerapp.view.Authentication.IAuthView;
import com.google.firebase.auth.FirebaseAuth;


public class SignInFragment extends Fragment implements IAuthView {

    Button loginBtn;
    EditText emailTxt;
    EditText passTxt;
    AuthPresenter presenter;
    Context context;

    public SignInFragment() {

        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Log.d("TAG", "onCreateView: ");
        loginBtn = view.findViewById(R.id.loginBtn);
        emailTxt = view.findViewById(R.id.emailTxt);
        passTxt = view.findViewById(R.id.passTxt);
        presenter = new AuthPresenter(FirebaseAuth.getInstance(),this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass ;
                email = String.valueOf(emailTxt.getText());
                pass = String.valueOf(passTxt.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity().getApplicationContext(), "You must enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(context.getApplicationContext(), "You must enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.onClick( email ,  pass);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUi(String destination) {
        switch(destination){
            case"signIn":
                Navigation.findNavController(loginBtn).navigate(R.id.action_signInFragment_to_signUpFragment);
                break;
        }
    }
}