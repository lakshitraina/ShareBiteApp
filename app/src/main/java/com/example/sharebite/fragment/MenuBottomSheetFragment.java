package com.example.sharebite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.sharebite.R;
import com.example.sharebite.adapter.MenuAdapter;
import com.example.sharebite.databinding.FragmentMenuBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentMenuBottomSheetBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        binding.buttonBack.setOnClickListener(v -> dismiss());

        // Define menu items, prices, and images
        List<String> menuFoodName = Arrays.asList("Burger", "Momo", "Sandwich", "Burger", "Momo", "Sandwich");
        List<String> menuItemPrice = Arrays.asList("Rs.25", "Rs.60", "Rs.30", "Rs.25", "Rs.60", "Rs.30");
        List<Integer> menuImage = Arrays.asList(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6
        );

        // Create an adapter with the data
        MenuAdapter adapter = new MenuAdapter(
                new ArrayList<>(menuFoodName),
                new ArrayList<>(menuItemPrice),
                new ArrayList<>(menuImage),
                requireContext()
        );

        // Set up the RecyclerView
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}
