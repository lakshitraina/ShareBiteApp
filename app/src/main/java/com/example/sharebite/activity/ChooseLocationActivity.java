package com.example.sharebite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityChooseLocationBinding;

public class ChooseLocationActivity extends AppCompatActivity {

    private ActivityChooseLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityChooseLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button button= (Button) findViewById(R.id.doneButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChooseLocationActivity.this,LoginActivity.class));
            }
        });


        // List of locations
        String[] locationList = new String[]{
                "Lovely Professional University, Jalandhar", "Bh-1", "Bh-2", "Bh-3", "Bh-4", "Bh-5", "Bh-6", "Bh-7",
                "Gh-1", "Gh-2", "Gh-3", "Gh-4", "Gh-5", "Gh-6", "Gh-7"
        };

        // Create adapter and set to AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locationList);
        AutoCompleteTextView autoCompleteTextView = binding.listOfLocation;
        autoCompleteTextView.setAdapter(adapter);
    }
}
