package com.example.myapplication.config;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.components.DaggerRetrofitComponent;
import com.example.myapplication.components.DaggerStatsServiceComponent;
import com.example.myapplication.components.RetrofitComponent;
import com.example.myapplication.components.StatsServiceComponent;
import com.example.myapplication.modules.APIModule;
import com.example.myapplication.modules.ServiceModule;


public class FruitApplication extends Application {

    private RetrofitComponent retrofitComponent;
    private StatsServiceComponent statsServiceComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        FruitApplication.context = getApplicationContext();

        retrofitComponent = DaggerRetrofitComponent.builder()
                .aPIModule(new APIModule())
                .build();

        statsServiceComponent = DaggerStatsServiceComponent.builder()
                .serviceModule(new ServiceModule())
                .build();
    }

    public RetrofitComponent getRetroComponent() {
        return retrofitComponent;
    }

    public StatsServiceComponent getStatsServiceComponent() {
        return statsServiceComponent;
    }

    public static Context getAppContext() {
        return FruitApplication.context;
    }
}
