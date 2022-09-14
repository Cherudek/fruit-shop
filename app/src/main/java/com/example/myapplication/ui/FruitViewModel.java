package com.example.myapplication.ui;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.client.APIClient;
import com.example.myapplication.client.APIInterface;
import com.example.myapplication.pojo.Fruit;
import com.example.myapplication.pojo.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FruitViewModel extends ViewModel {

    private List<Fruit> fruits = new ArrayList<>();
    private final APIInterface apiInterface;

    public FruitViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public List<Fruit> getFruits() {
        Call<Result> call = apiInterface.doGetFruit();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                System.out.println(
                        "******************"
                        + response.isSuccessful()
                        + " "
                        + response.message()
                );
                fruits = response.body().fruit;
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                call.cancel();
            }
        });
        return fruits;
    }
}
