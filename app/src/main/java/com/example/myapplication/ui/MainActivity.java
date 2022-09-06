package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.client.APIClient;
import com.example.myapplication.client.APIInterface;
import com.example.myapplication.pojo.Fruit;
import com.example.myapplication.pojo.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = findViewById(R.id.textView);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Result> call = apiInterface.doGetFruit();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result fruits = response.body();
                System.out.println("*********************************");
                System.out.println("Fruits: " + call);
                System.out.println("Code: " + response.code());
                System.out.println("Message: " + response.message());
                System.out.println("Success: " + response.isSuccessful());
                System.out.println("Fruits: " + fruits.fruit.toString());

                StringBuilder fruitView = new StringBuilder();

                for (Fruit fruit : fruits.fruit) {
                    fruitView.append(" ").append(fruit.getType()).append(" ").append(fruit.getPrice());
                }

                responseText.setText(fruitView);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                call.cancel();

            }
        });

    }
}