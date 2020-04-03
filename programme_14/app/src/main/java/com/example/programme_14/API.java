package com.example.programme_14;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface API {
    @GET("/api")
    Call<ResponseBody> getWeather(@QueryMap Map<String,Object> parm);
}
