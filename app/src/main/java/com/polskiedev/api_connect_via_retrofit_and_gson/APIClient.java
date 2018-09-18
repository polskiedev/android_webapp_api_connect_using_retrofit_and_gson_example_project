package com.polskiedev.api_connect_via_retrofit_and_gson;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "http://10.0.2.2/android-api/";
    public static Retrofit retrofit = null;

    public static Retrofit getAPIClient() {
       if(retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
       }

       return retrofit;
    }
}
