package com.example.sharebite.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String foodName = getIntent().getStringExtra("MenuItemName");
        int foodImage = getIntent().getIntExtra("MenuItemImage", 0);


        binding.detailFoodName.setText(foodName);
        binding.detailFoodImage.setImageResource(foodImage);


        binding.imageButton.setOnClickListener(v -> finish());

    }



}