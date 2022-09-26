package com.example.myapplication.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.client.APIInterface;
import com.example.myapplication.config.FruitApplication;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StatsService {

    private static final String LOG_TAG = StatsService.class.getSimpleName();
    private static Call<String> call;
    @Inject
    @Named("stats")
    public Retrofit retrofit;
    private MutableLiveData<String> result;

    @Inject
    public StatsService() {
        ((FruitApplication) FruitApplication.getAppContext()).getRetroComponent().inject(this);
    }

    public void getData(String event, long data) {
        result = new MutableLiveData<>();
        call = retrofit.create(APIInterface.class).getStats(event, data);
        loadStats();
    }

    public void loadStats() {
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.d(LOG_TAG, "Generating Stats: " + response.isSuccessful());
                Log.d(LOG_TAG, "Generating Stats: " + response.code());
                Log.d(LOG_TAG, "Generating Stats: " + response.message());
                result.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.d(LOG_TAG, "Error Generating Stats: " + t.getMessage());
                call.cancel();
            }
        });
    }
}
