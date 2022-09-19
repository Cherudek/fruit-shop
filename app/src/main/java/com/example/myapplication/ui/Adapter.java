package com.example.myapplication.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.pojo.Fruit;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public static final String LOG_TAG = Adapter.class.getName();

    private final List<Fruit> fruits;
    private final AdapterOnClickHandler onClickHandler;

    public Adapter(AdapterOnClickHandler onClickHandler, List<Fruit> fruits) {
        this.onClickHandler = onClickHandler;
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        List<Fruit> fruits = this.fruits;
        Fruit fruit = fruits.get(position);
        TextView textView = holder.textView;
        String type = fruit.getType();
        String fruitCap = type.substring(0, 1).toUpperCase().concat(type.substring(1));
        textView.setText(fruitCap);
    }

    @Override
    public int getItemCount() {
        Log.d(LOG_TAG, "Fruit items count: " + fruits.size());
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Fruit fruit = fruits.get(adapterPosition);
            onClickHandler.onClick(fruit.getType(), fruit.getPrice(), fruit.getWeight());
        }
    }

    public interface AdapterOnClickHandler {
        void onClick(String type, Integer price, Integer weight);
    }
}


