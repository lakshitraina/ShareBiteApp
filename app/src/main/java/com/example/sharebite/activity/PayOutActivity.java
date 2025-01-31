package com.example.sharebite.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharebite.CongratsBottomSheet;
import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityPayOutBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PayOutActivity extends AppCompatActivity {

    private ActivityPayOutBinding binding;
    private FirebaseFirestore db;  // Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding object
        binding = ActivityPayOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firestore instance
        db = FirebaseFirestore.getInstance();

        // Set onClickListener for "Place My Order" button
        binding.PlaceMyOrder.setOnClickListener(v -> {
            // Increment order count in Firestore
            incrementOrderCount();

            // Show the bottom sheet once the order is placed
            CongratsBottomSheet bottomSheetDialog = new CongratsBottomSheet();
            bottomSheetDialog.show(getSupportFragmentManager(), "Test");
        });

        // Apply window insets to adjust padding dynamically
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            }
            return insets;
        });
    }

    private void incrementOrderCount() {
        // Reference to the order count document in Firestore
        db.collection("order_count").document("count")
                .update("count", FieldValue.increment(1))  // Atomically increment the "count" field
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Order count successfully incremented!");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error incrementing order count", e);
                });
    }
}
