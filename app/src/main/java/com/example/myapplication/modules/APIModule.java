package com.example.myapplication.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class APIModule {

    @Provides
    @Singleton
    static OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    @Named("fruits")
    static Retrofit provideRetrofit(OkHttpClient client) {

        String BASE_URL = "https://raw.githubusercontent.com";
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    @Named("stats")
    static Retrofit provideRetrofitData(OkHttpClient client) {

        String BASE_URL = "https://raw.githubusercontent.com";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
    }
}
