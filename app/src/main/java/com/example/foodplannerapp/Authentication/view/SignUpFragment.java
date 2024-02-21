package com.example.foodplannerapp.Authentication.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplannerapp.Authentication.Presenter.AuthPresenter;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Authentication.AuthRepository;
import com.example.foodplannerapp.Authentication.IAuthView;
import com.example.foodplannerapp.view.MainActivity;




public class SignUpFragment extends Fragment implements IAuthView {
    EditText nameEditTxt , emailEditTxt , passwordEditTxt;

    Button registerBtn;
    AuthPresenter presenter;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        nameEditTxt = view.findViewById(R.id.name_edit_text);
        emailEditTxt = view.findViewById(R.id.email_edit_text);
        passwordEditTxt = view.findViewById(R.id.password_edit_text);
        registerBtn = view.findViewById(R.id.registerBtn);
        presenter = new AuthPresenter(AuthRepository.getInstance(),this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass , userName ;
                userName = String.valueOf(nameEditTxt.getText());
                email = String.valueOf(emailEditTxt.getText());
                pass = String.valueOf(passwordEditTxt.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity().getApplicationContext(), "You must enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getActivity().getApplicationContext(), "You must enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.onRegisterClick( email ,  pass , userName);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUi(String userId) {

        intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }
}