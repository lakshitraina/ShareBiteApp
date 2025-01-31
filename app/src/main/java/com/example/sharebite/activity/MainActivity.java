package com.example.sharebite.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;  // Change this to AppCompatActivity
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.sharebite.Notification_Bottom_Fragment;
import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {  // Change this to AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Enable edge-to-edge display
        enableEdgeToEdge();

        // Find the NavController
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        // Find the BottomNavigationView and set it up with the NavController
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navController);

        // Set onClickListener for the notification button
        binding.notificationButton.setOnClickListener(v -> {
            Notification_Bottom_Fragment bottomSheetDialog = new Notification_Bottom_Fragment();
            bottomSheetDialog.show(getSupportFragmentManager(), "Test");  // This should now work
        });
    }

    // You can implement this method if needed for edge-to-edge functionality
    private void enableEdgeToEdge() {
        // Handle edge-to-edge setup if needed
    }
}
