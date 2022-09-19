package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.config.FruitApplication;
import com.example.myapplication.pojo.Fruit;
import com.example.myapplication.R;
import com.example.myapplication.service.StatsService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Adapter.AdapterOnClickHandler {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private long displayTime;
    private long startTime;
    @Inject
    public StatsService statsService;
    FruitViewModel fruitViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();
    }

    private void setUpView() {
        setContentView(R.layout.activity_main);
        RecyclerView fruitView = findViewById(R.id.recycler_view);
        fruitView.setLayoutManager(new LinearLayoutManager(this));
        ((FruitApplication) getApplication()).getStatsServiceComponent().inject(this);

        fruitViewModel = new ViewModelProvider(this).get(FruitViewModel.class);
        fruitViewModel.getFruit().observe(MainActivity.this, fruitList -> {
            startTime = System.currentTimeMillis();
            Log.d(LOG_TAG, "Observe New Fruits: " + fruitViewModel.getFruit());
            Adapter adapter = new Adapter(MainActivity.this, fruitList);
            fruitView.setAdapter(adapter);
        });
        generateDisplayTime(fruitView, startTime);
    }

    @Override
    public void onClick(String type, Integer price, Integer weight) {
        Class<DetailActivity> detail = DetailActivity.class;
        Fruit fruit = new Fruit();
        fruit.setType(type);
        fruit.setPrice(price);
        fruit.setWeight(weight);
        Log.d(LOG_TAG, "Detail of: " + fruit);
        Intent detailIntent = new Intent(this, detail);
        detailIntent.putExtra(Intent.EXTRA_TEXT, fruit);
        startActivity(detailIntent);
    }

    private void generateDisplayTime(RecyclerView view, long startTime) {
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (() -> displayTime = System.currentTimeMillis() - startTime);
        statsService.getData("display", displayTime);
    }
}