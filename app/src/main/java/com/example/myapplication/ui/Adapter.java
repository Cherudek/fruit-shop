package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.pojo.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public static final String LOG_TAG = Adapter.class.getName();
    private final List<Fruit> fruits;
    private AdapterOnClickHandler onClickHandler;

    public Adapter(AdapterOnClickHandler onClickHandler, List<Fruit> fruits) {
        this.onClickHandler = onClickHandler;
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(fruit.getType());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
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


