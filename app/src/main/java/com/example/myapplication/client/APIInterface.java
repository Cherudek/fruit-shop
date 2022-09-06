package com.example.myapplication.client;

import com.example.myapplication.pojo.Fruit;
import com.example.myapplication.pojo.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/fmtvp/recruit-test-data/master/data.json")
    Call<Result> doGetFruit();
}
