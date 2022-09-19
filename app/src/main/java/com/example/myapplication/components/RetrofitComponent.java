package com.example.myapplication.components;

import com.example.myapplication.modules.APIModule;
import com.example.myapplication.service.StatsService;
import com.example.myapplication.ui.FruitViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {APIModule.class})
public interface RetrofitComponent {
    void inject(FruitViewModel viewModel);
    void inject(StatsService statsService);
}
