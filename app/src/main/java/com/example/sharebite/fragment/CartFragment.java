package com.example.sharebite.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sharebite.R;
import com.example.sharebite.activity.PayOutActivity;
import com.example.sharebite.adapter.CartAdapter;
import com.example.sharebite.databinding.FragmentCartBinding;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private ArrayList<String> cartFoodName = new ArrayList<>();
    private ArrayList<String> cartItemPrice = new ArrayList<>();
    private ArrayList<Integer> cartImage = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize the binding here (correct place)
        binding = FragmentCartBinding.inflate(inflater, container, false);

        // Retrieve data from SharedPreferences
        SharedPreferences preferences = requireContext().getSharedPreferences("Cart", Context.MODE_PRIVATE);
        String foodName = preferences.getString("foodName", "Unknown");
        String foodPrice = preferences.getString("foodPrice", "₹0");
        int foodImage = preferences.getInt("foodImage", R.drawable.placeholder); // Default placeholder image

        // Add the food item to the cart list
        cartFoodName.add(foodName);
        cartItemPrice.add(foodPrice);
        cartImage.add(foodImage);

        // Set up the RecyclerView with CartAdapter
        CartAdapter adapter = new CartAdapter(cartFoodName, cartItemPrice, cartImage);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRecyclerView.setAdapter(adapter);

        // Proceed to checkout button
        binding.proceedButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PayOutActivity.class);
            startActivity(intent);
        });

        // Return the root view of the fragment
        return binding.getRoot();
    }
}
