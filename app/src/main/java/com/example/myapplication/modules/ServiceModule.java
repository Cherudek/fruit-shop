package com.example.myapplication.modules;

import com.example.myapplication.service.StatsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    static StatsService provideStatsService() {
        return new StatsService();
    }
}
