package com.example.sharebite.activity;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharebite.CongratsBottomSheet;
import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityPayOutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PayOutActivity extends AppCompatActivity {

    private ActivityPayOutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Initialize the binding object
        binding = ActivityPayOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set onClickListener for "Place My Order" button to show bottom sheet
        binding.PlaceMyOrder.setOnClickListener(v -> {
            CongratsBottomSheet bottomSheetDialog = new CongratsBottomSheet();
            bottomSheetDialog.show(getSupportFragmentManager(), "Test");
        });

        // Apply window insets to adjust padding dynamically
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get system bar insets (top, left, right, bottom)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // For Android 11 and above, we get the insets directly from WindowInsetsCompat
                v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            }
            return insets;
        });
    }
}
