package com.example.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/weather_v2")
    Call<ResponseBody> getJson();
}
