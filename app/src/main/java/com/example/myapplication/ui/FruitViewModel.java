package com.example.myapplication.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
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

    private MutableLiveData<List<Fruit>> fruits;
    private final APIInterface apiInterface;

    public FruitViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public MutableLiveData<List<Fruit>> getFruit() {
            fruits = new MutableLiveData<>();
            loadFruits();
            return fruits;
    }

    public void loadFruits() {
        List<Fruit> newFruits = new ArrayList<>();
        Call<Result> call = apiInterface.doGetFruit();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(LOG_TAG, "Fetching Fruits: " + response.isSuccessful());
                Log.d(LOG_TAG, "Fetching Fruits: " + response.message());
                Log.d(LOG_TAG, "Fetching Fruits: " + response.body().fruit);
                newFruits.addAll(response.body().fruit);
                Log.d(LOG_TAG, "Set New Fruit List: " + newFruits);
                fruits.setValue(newFruits);
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
