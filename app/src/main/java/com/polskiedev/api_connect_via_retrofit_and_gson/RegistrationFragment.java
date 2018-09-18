package com.polskiedev.api_connect_via_retrofit_and_gson;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText name, username, password;
    private Button btnRegister;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        return view;
    }

    public void performRegistration() {
        String sName = name.getText().toString();
        String sUsername = username.getText().toString();
        String sPassword = password.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegistration(sName, sUsername, sPassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.displayToast("Registration Success!");
                } else if(response.body().getResponse().equals("exists")) {
                    MainActivity.prefConfig.displayToast("User already exists!");
                } else if(response.body().getResponse().equals("error")) {
                    MainActivity.prefConfig.displayToast("Something went wrong...");
                } else {
                    MainActivity.prefConfig.displayToast(response.body().getResponse());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        name.setText("");
        username.setText("");
        password.setText("");

    }


}

