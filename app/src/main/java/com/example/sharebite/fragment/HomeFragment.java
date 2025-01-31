package com.example.sharebite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.sharebite.R;
import com.example.sharebite.adapter.PopularAdapter;
import com.example.sharebite.databinding.FragmentHomeBinding;
import com.example.sharebite.model.FoodItem;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private FirebaseFirestore db;
    private List<String> foodName = new ArrayList<>();
    private List<String> price = new ArrayList<>();
    private List<String> popularFoodImages = new ArrayList<>();  // List<String> for image URLs

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Fetch data from Firestore
        fetchFoodDataFromFirestore();

        binding.viewAllMenu.setOnClickListener(v -> {
            com.example.sharebite.fragment.MenuBottomSheetFragment bottomSheetDialog = new com.example.sharebite.fragment.MenuBottomSheetFragment();
            bottomSheetDialog.show(getParentFragmentManager(), "Test");
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Image Slider setup
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT);

        // Item click listener for the image slider
        binding.imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int position) {
                // Double click functionality not yet implemented
            }

            @Override
            public void onItemSelected(int position) {
                String itemMessage = "Selected Image " + position;
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchFoodDataFromFirestore() {
        // Reference to the "food" collection in Firestore
        CollectionReference foodCollection = db.collection("food");

        // Fetch the data
        foodCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Clear previous data before adding new data
                foodName.clear();
                price.clear();
                popularFoodImages.clear();

                for (var document : task.getResult()) {
                    // Get the data from each document
                    FoodItem foodItem = document.toObject(FoodItem.class);
                    if (foodItem != null) {
                        String itemName = foodItem.getName();
                        String itemPrice = foodItem.getPrice();
                        String itemImageUrl = foodItem.getImageUrl();

                        // Check for duplicate item (optional - can also use Set)
                        if (!foodName.contains(itemName)) {
                            foodName.add(itemName);
                            price.add(itemPrice);
                            popularFoodImages.add(itemImageUrl != null ? itemImageUrl : "placeholder_url");
                        }
                    }
                }

                // After the data is fetched, update the adapter
                PopularAdapter adapter = new PopularAdapter(foodName, price, popularFoodImages, requireContext());
                binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                binding.PopularRecyclerView.setAdapter(adapter);

            } else {
                // Handle failure
                Toast.makeText(requireContext(), "Error getting data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
