package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.pojo.Fruit;

public class MainActivity extends AppCompatActivity implements Adapter.AdapterOnClickHandler {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();
    }

    private void setUpView() {
        setContentView(R.layout.activity_main);
        RecyclerView fruitView = findViewById(R.id.recycler_view);
        FruitViewModel viewModel = new ViewModelProvider(this).get(FruitViewModel.class);
        Log.d(LOG_TAG, "Fruits: " + viewModel.getFruits().toString());
        fruitView.setAdapter(new Adapter(this, viewModel.getFruits()));
        fruitView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(String type, Integer price, Integer weight) {
        Context context = this;
        Class<DetailActivity> detail = DetailActivity.class;
        Fruit fruit = new Fruit();
        fruit.setType(type);
        fruit.setPrice(price);
        fruit.setWeight(weight);
        Intent detailIntent = new Intent(context, detail);
        detailIntent.putExtra(Intent.EXTRA_TEXT, fruit);
        startActivity(detailIntent);
    }
}