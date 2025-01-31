package com.example.sharebite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sharebite.R;
import com.example.sharebite.adapter.MenuAdapter;
import com.example.sharebite.databinding.FragmentSearchBinding;
import com.example.sharebite.model.FoodItem;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private MenuAdapter adapter;
    private FirebaseFirestore db;

    // Original data lists for the menu
    private List<String> originalMenuFoodName = new ArrayList<>();
    private List<String> originalMenuItemPrice = new ArrayList<>();
    private List<Integer> originalMenuImage = new ArrayList<>();

    // Filtered data lists for search functionality
    private List<String> filteredMenuFoodName = new ArrayList<>();
    private List<String> filteredMenuItemPrice = new ArrayList<>();
    private List<Integer> filteredMenuImage = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance(); // Initialize Firestore
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        // Set up the RecyclerView adapter
        adapter = new MenuAdapter(filteredMenuFoodName, filteredMenuItemPrice, filteredMenuImage, requireContext());
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        // Setup the search view listener
        setupSearchView();

        // Fetch data from Firestore
        fetchMenuDataFromFirestore();

        return binding.getRoot();
    }

    // Fetch menu data from Firestore
    private void fetchMenuDataFromFirestore() {
        CollectionReference foodCollection = db.collection("food");  // Reference to your Firestore collection

        foodCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Clear existing data in case of a new fetch
                originalMenuFoodName.clear();
                originalMenuItemPrice.clear();
                originalMenuImage.clear();

                // Iterate through Firestore documents and populate the lists
                for (var document : task.getResult()) {
                    FoodItem foodItem = document.toObject(FoodItem.class);  // Convert the document to a FoodItem object
                    if (foodItem != null) {
                        originalMenuFoodName.add(foodItem.getName());
                        originalMenuItemPrice.add(foodItem.getPrice());
                        originalMenuImage.add(R.drawable.menu1);  // Use a placeholder image or use Glide/Picasso for image loading
                    }
                }

                // After data is fetched, update the filtered lists and notify the adapter
                showAllMenu();
            } else {
                Toast.makeText(requireContext(), "Error getting data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Show all menu items (use this to show the full list)
    private void showAllMenu() {
        filteredMenuFoodName.clear();
        filteredMenuItemPrice.clear();
        filteredMenuImage.clear();

        filteredMenuFoodName.addAll(originalMenuFoodName);
        filteredMenuItemPrice.addAll(originalMenuItemPrice);
        filteredMenuImage.addAll(originalMenuImage);

        adapter.notifyDataSetChanged();
    }

    // Setup the search functionality for the menu
    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterMenuItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMenuItems(newText);
                return true;
            }
        });
    }

    // Filter the menu items based on the search query
    private void filterMenuItems(String query) {
        filteredMenuFoodName.clear();
        filteredMenuItemPrice.clear();
        filteredMenuImage.clear();

        for (int i = 0; i < originalMenuFoodName.size(); i++) {
            String foodName = originalMenuFoodName.get(i);
            if (foodName.toLowerCase().contains(query != null ? query.toLowerCase() : "")) {
                filteredMenuFoodName.add(foodName);
                filteredMenuItemPrice.add(originalMenuItemPrice.get(i));
                filteredMenuImage.add(originalMenuImage.get(i));
            }
        }

        adapter.notifyDataSetChanged();
    }
}
