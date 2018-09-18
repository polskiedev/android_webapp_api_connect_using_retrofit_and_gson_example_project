package com.polskiedev.api_connect_via_retrofit_and_gson;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView linkRegister;
    private EditText username, password;
    private Button btnLogin;

    OnLoginFormActivityListener loginFormActivityListener;

    public interface OnLoginFormActivityListener {
        public void performRegister();
        public void performLogin(String name);
    };

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        linkRegister = view.findViewById(R.id.link_register);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
        String sUsername = username.getText().toString();
        String sPassword = password.getText().toString();

        Call<User> call = MainActivity.apiInterface.performUserLogin(sUsername, sPassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }else if(response.body().getResponse().equals("failed")){
                    MainActivity.prefConfig.displayToast("Login Failed... Please try again...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        username.setText("");
        password.setText("");
    }

}
