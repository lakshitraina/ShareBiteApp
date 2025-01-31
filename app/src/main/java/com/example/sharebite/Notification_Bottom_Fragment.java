package com.example.sharebite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sharebite.adapter.NotificationAdapter;
import com.example.sharebite.databinding.FragmentNotificationBottomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class Notification_Bottom_Fragment extends BottomSheetDialogFragment {

    private FragmentNotificationBottomBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBottomBinding.inflate(inflater, container, false);

        // Explicitly declare these as ArrayList
        ArrayList<String> notifications = new ArrayList<>();
        notifications.add("Your Order has been Canceled Successfully");
        notifications.add("Order is Out for Delivery");
        notifications.add("Congratulations, Your Order had been Placed");

        ArrayList<Integer> notificationImages = new ArrayList<>();
        notificationImages.add(R.drawable.sademoji);
        notificationImages.add(R.drawable.truck);
        notificationImages.add(R.drawable.congrats);

        // Pass ArrayList to the adapter
        NotificationAdapter adapter = new NotificationAdapter(notifications, notificationImages);

        binding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.notificationRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}
