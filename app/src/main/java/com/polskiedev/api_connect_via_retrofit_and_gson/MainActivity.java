package com.polskiedev.api_connect_via_retrofit_and_gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.polskiedev.api_connect_via_retrofit_and_gson.R.id.fragment_container;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener, WelcomeFragment.OnLogoutListener {

    public static PrefConfig prefConfig;
    public  static APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig = new PrefConfig( this);
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);

        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }

            if(prefConfig.readLoginStatus()) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new WelcomeFragment())
                        .commit();
            } else {

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }

        //Video URL: https://www.youtube.com/watch?v=d5jfNSFu45U
        //Video Title: Android Login/Registration with MySQL Database Example Using Retrofit
        //YouTube Channel: PRABEESH R K (https://www.youtube.com/channel/UCfQkNueQenRQQ1NnCBe6eQQ)
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).commit();
    }

    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
    }
}
