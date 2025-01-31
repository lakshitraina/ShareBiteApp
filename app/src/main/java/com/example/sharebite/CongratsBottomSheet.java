package com.example.sharebite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sharebite.activity.MainActivity;
import com.example.sharebite.databinding.FragmentCongratsBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CongratsBottomSheet extends BottomSheetDialogFragment {

    private FragmentCongratsBottomSheetBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout and bind it to the binding object
        binding = FragmentCongratsBottomSheetBinding.inflate(inflater, container, false);

        // Set an OnClickListener to go back to the MainActivity when the button is clicked
        binding.goHome.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MainActivity.class);
            startActivity(intent);
        });

        // Return the root view
        return binding.getRoot();
    }
}
