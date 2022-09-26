package com.example.myapplication.components;

import com.example.myapplication.modules.APIModule;
import com.example.myapplication.service.StatsService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {APIModule.class})
public interface ApplicationComponent {
    void inject(StatsService statsService);
}
