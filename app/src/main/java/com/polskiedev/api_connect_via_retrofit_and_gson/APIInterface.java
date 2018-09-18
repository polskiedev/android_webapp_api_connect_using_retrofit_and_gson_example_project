package com.polskiedev.api_connect_via_retrofit_and_gson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    //@GET("create_user.php")
    @GET("test_registration.php")
    Call<User> performRegistration(@Query("name") String name, @Query("username") String username, @Query("password") String password);

    @GET("test_login.php")
    Call<User> performUserLogin(@Query("username") String username, @Query("password") String password);

}