package com.example.retrofitdemo2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/user.json")
    Call<List<JsonResult>> getJson();
}
