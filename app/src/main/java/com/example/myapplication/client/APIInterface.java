package com.example.myapplication.client;

import com.example.myapplication.pojo.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/fmtvp/recruit-test-data/master/data.json")
    Call<Result> doGetFruit();

    @GET("/fmtvp/recruit-test-data/master/stats")
    Call<String> getStats(@Query("event") String event,
                        @Query("data") long data);

}
