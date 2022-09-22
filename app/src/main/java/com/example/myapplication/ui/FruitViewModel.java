package com.example.myapplication.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.client.APIInterface;
import com.example.myapplication.config.FruitApplication;
import com.example.myapplication.pojo.Fruit;
import com.example.myapplication.pojo.Result;
import com.example.myapplication.service.StatsService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FruitViewModel extends AndroidViewModel {

    private static final String LOG_TAG = FruitViewModel.class.getSimpleName();

    private MutableLiveData<List<Fruit>> fruits;

    @Inject
    @Named("fruits")
    public Retrofit retrofit;

    @Inject
    public StatsService statsService;

    public FruitViewModel(@NonNull Application application) {
        super(application);
        ((FruitApplication) application).getRetroComponent().inject(this);
    }

    public MutableLiveData<List<Fruit>> getFruit() {
            fruits = new MutableLiveData<>();
            loadFruits();
            return fruits;
    }

    public void loadFruits() {
        List<Fruit> newFruits = new ArrayList<>();
        Call<Result> call = retrofit.create(APIInterface.class).doGetFruit();
        long startTime = System.currentTimeMillis();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                long responseTime = System.currentTimeMillis() - startTime;
                newFruits.addAll(response.body().fruit);
                fruits.setValue(newFruits);
                generateLoadStats("load", responseTime);
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                generateLoadStats("error", 0);
                call.cancel();
            }
        });
    }

    public void generateLoadStats(String event, long data) {
        Log.d(LOG_TAG, "Generating Load Stats");
        statsService.getData(event, data);
    }
}
