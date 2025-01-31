package com.example.sharebite.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityStartBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        if (currentUser != null) {
            Intent intent = new Intent(StartActivity.this, MainActivity.class); // Or whichever activity you want to go to
            startActivity(intent);
            finish();
            return;
        }


        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
