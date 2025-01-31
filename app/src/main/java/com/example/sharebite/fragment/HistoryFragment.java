package com.example.sharebite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sharebite.R;
import com.example.sharebite.adapter.BuyAgainAdapter;
import com.example.sharebite.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private FragmentHistoryBinding binding;
    private BuyAgainAdapter buyAgainAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        setUpRecyclerView();
        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        // Using ArrayList instead of List for the adapter
        ArrayList<String> buyAgainFoodName = new ArrayList<>();
        buyAgainFoodName.add("Food1");
        buyAgainFoodName.add("Food2");
        buyAgainFoodName.add("Food3");

        ArrayList<String> buyAgainFoodPrice = new ArrayList<>();
        buyAgainFoodPrice.add("₹25");
        buyAgainFoodPrice.add("₹60");
        buyAgainFoodPrice.add("₹30");

        ArrayList<Integer> buyAgainFoodImage = new ArrayList<>();
        buyAgainFoodImage.add(R.drawable.menu1);
        buyAgainFoodImage.add(R.drawable.menu2);
        buyAgainFoodImage.add(R.drawable.menu3);

        buyAgainAdapter = new BuyAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage);
        binding.BuyAgainRecyclerView.setAdapter(buyAgainAdapter);
        binding.BuyAgainRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
