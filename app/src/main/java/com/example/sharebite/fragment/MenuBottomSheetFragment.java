package com.example.sharebite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sharebite.R;
import com.example.sharebite.adapter.MenuAdapter;
import com.example.sharebite.databinding.FragmentMenuBottomSheetBinding;
import com.example.sharebite.model.FoodItem;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentMenuBottomSheetBinding binding;
    private FirebaseFirestore db;

    private List<String> menuFoodName = new ArrayList<>();
    private List<String> menuItemPrice = new ArrayList<>();
    private List<String> menuFoodImages = new ArrayList<>(); // To hold image URLs

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance(); // Initialize Firebase Firestore
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        // Set up the back button click
        binding.buttonBack.setOnClickListener(v -> dismiss());

        // Fetch data from Firestore
        fetchMenuDataFromFirestore();

        return binding.getRoot();
    }

    // Fetch menu data from Firestore
    private void fetchMenuDataFromFirestore() {
        // Reference to the "menu" collection in Firestore
        CollectionReference foodCollection = db.collection("menu");

        foodCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Clear any existing data in the lists before adding new data
                menuFoodName.clear();
                menuItemPrice.clear();
                menuFoodImages.clear();

                // Loop through the documents and get the data
                for (var document : task.getResult()) {
                    FoodItem foodItem = document.toObject(FoodItem.class);
                    if (foodItem != null) {
                        String foodName = foodItem.getName();
                        String price = foodItem.getPrice();
                        String imageUrl = foodItem.getImageUrl();

                        // Avoid duplicates (optional - you can also use a Set to prevent duplicates)
                        if (!menuFoodName.contains(foodName)) {
                            menuFoodName.add(foodName);
                            menuItemPrice.add(price);
                            // Default to a placeholder image if no URL is available
                            menuFoodImages.add(imageUrl != null ? imageUrl : "https://example.com/placeholder_image.jpg");
                        }
                    }
                }

                // After data is fetched, set up the adapter and RecyclerView
                MenuAdapter adapter = new MenuAdapter(menuFoodName, menuItemPrice, menuFoodImages, requireContext());
                binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                binding.menuRecyclerView.setAdapter(adapter);

            } else {
                // Handle Firestore failure
                Toast.makeText(requireContext(), "Error getting data from Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            // Handle the case where Firestore query fails
            Toast.makeText(requireContext(), "Firestore Query Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
}
