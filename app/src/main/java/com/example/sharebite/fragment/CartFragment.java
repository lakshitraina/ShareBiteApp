package com.example.sharebite.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sharebite.R;
import com.example.sharebite.activity.PayOutActivity;
import com.example.sharebite.adapter.CartAdapter;
import com.example.sharebite.databinding.FragmentCartBinding;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize ViewBinding
        binding = FragmentCartBinding.inflate(inflater, container, false);

        // Sample data for cart
        ArrayList<String> cartFoodName = new ArrayList<>();
        cartFoodName.add("Burger");
        cartFoodName.add("Momo");
        cartFoodName.add("Sandwich");

        ArrayList<String> cartItemPrice = new ArrayList<>();
        cartItemPrice.add("₹25");
        cartItemPrice.add("₹60");
        cartItemPrice.add("₹30");

        ArrayList<Integer> cartImage = new ArrayList<>();
        cartImage.add(R.drawable.menu1);
        cartImage.add(R.drawable.menu2);
        cartImage.add(R.drawable.menu3);

        // Set up the RecyclerView with CartAdapter
        CartAdapter adapter = new CartAdapter(cartFoodName, cartItemPrice, cartImage);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRecyclerView.setAdapter(adapter);

        // Proceed button click listener
        binding.proceedButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PayOutActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}