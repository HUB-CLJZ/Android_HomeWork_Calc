package com.example.competition_dislocation16;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/v2/5e2273e32f00009600222598")
    Call<List<CarBean>> getData();
}