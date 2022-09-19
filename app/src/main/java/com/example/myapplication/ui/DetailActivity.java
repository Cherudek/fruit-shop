package com.example.myapplication.ui;

import static com.example.myapplication.utils.StringFormatter.capitalize;
import static com.example.myapplication.utils.StringFormatter.priceFormatter;
import static com.example.myapplication.utils.StringFormatter.weightFormatter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.pojo.Fruit;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Fruit fruit = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

        Log.d(LOG_TAG, "Reading Main Activity Message: " + fruit);

        TextView type = findViewById(R.id.type);
        type.setText(capitalize(fruit.getType()));

        TextView priceView = findViewById(R.id.price);
        priceView.setText(priceFormatter(fruit.getPrice()));

        TextView weightView = findViewById(R.id.weight);
        weightView.setText(weightFormatter(fruit.getWeight()));
    }



}
