package com.example.myapplication.ui;

import android.util.Log;

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

    private static final String LOG_TAG = FruitViewModel.class.getSimpleName();

    private final List<Fruit> fruits = new ArrayList<>();
    private final APIInterface apiInterface;

    public FruitViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public List<Fruit> getFruits() {
        Call<Result> call = apiInterface.doGetFruit();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Log.d(LOG_TAG, "Fetching Fruits: " + response.isSuccessful());
                Log.d(LOG_TAG, "Fetching Fruits: " + response.message());
                Log.d(LOG_TAG, "Fetching Fruits: " + response.body().fruit);
                fruits.addAll(response.body().fruit);
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                call.cancel();
            }
        });
        Log.d(LOG_TAG, "Return Fruit List: " + fruits);
        return fruits;
    }
}
