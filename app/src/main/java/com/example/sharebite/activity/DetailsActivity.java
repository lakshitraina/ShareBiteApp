package com.example.sharebite.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
        String foodPrice = getIntent().getStringExtra("MenuItemPrice");

        // Set the details of the food item
        binding.detailFoodName.setText(foodName);
        binding.detailFoodImage.setImageResource(foodImage);

        binding.imageButton.setOnClickListener(v -> finish());

        // Add the item to the cart when the button is clicked
        binding.acart.setOnClickListener(v -> {
            // Save the food item to SharedPreferences
            SharedPreferences preferences = getSharedPreferences("Cart", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("foodName", foodName);
            editor.putString("foodPrice", foodPrice);
            editor.putInt("foodImage", foodImage);
            editor.apply();

            // Optionally, inform the user that the item has been added to the cart
            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}
