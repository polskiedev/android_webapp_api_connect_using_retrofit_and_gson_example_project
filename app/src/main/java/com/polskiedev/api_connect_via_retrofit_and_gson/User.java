package com.polskiedev.api_connect_via_retrofit_and_gson;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
}
