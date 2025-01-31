package com.example.sharebite.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sharebite.databinding.NotificationItemBinding;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private ArrayList<String> notification;
    private ArrayList<Integer> notificationImage;

    public NotificationAdapter(ArrayList<String> notification, ArrayList<Integer> notificationImage) {
        this.notification = notification;
        this.notificationImage = notificationImage;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout using ViewBinding
        NotificationItemBinding binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return notification.size();
    }

    // ViewHolder class to bind data to each item
    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private NotificationItemBinding binding;

        public NotificationViewHolder(NotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            // Bind notification data to views
            binding.notificationTextView.setText(notification.get(position));
            binding.notificationImageView.setImageResource(notificationImage.get(position));
        }
    }
}
