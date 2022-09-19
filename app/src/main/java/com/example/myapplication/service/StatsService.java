package com.example.myapplication.service;

import android.util.Log;

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
    public Retrofit apiInterface;
    private MutableLiveData<String> result;

    @Inject
    public StatsService() {
        Log.d(LOG_TAG, "Instantiating Stats Service");
        ((FruitApplication) FruitApplication.getAppContext()).getRetroComponent().inject(this);
    }

    public MutableLiveData<String> getData(String event, long data) {
        result = new MutableLiveData<>();
        call = apiInterface.create(APIInterface.class).getStats(event, data);
        loadStats();
        return result;
    }

    public void loadStats() {
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(LOG_TAG, "Generating Stats: " + response.isSuccessful());
                Log.d(LOG_TAG, "Generating Stats: " + response.code());
                Log.d(LOG_TAG, "Generating Stats: " + response.message());
                Log.d(LOG_TAG, "Generating Stats: " + response.body());
                result.setValue(response.body());
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(LOG_TAG, "Error Generating Stats: " + t.getMessage());
                call.cancel();
            }
        });
    }
}
