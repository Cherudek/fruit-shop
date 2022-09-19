package com.example.myapplication.components;

import com.example.myapplication.modules.ServiceModule;
import com.example.myapplication.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ServiceModule.class})
public interface StatsServiceComponent {
    void inject(MainActivity mainActivity);
}
