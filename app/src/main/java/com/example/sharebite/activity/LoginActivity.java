package com.example.sharebite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.ProgressBar;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        // Check if the user is already logged in
        if (mAuth.getCurrentUser() != null) {
            // User is already signed in, redirect to MainActivity
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        progressBar = findViewById(R.id.progressBar);

        binding.loginButton.setOnClickListener(v -> loginUser());

        binding.registerText.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void loginUser() {
        String emailText = binding.email.getText().toString();
        String passwordText = binding.password.getText().toString();

        if (emailText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(task -> {

                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
