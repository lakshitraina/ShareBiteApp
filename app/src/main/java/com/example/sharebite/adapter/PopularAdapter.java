package com.example.sharebite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharebite.R;
import com.example.sharebite.activity.DetailsActivity;
import com.example.sharebite.databinding.PopularItemBinding;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private List<String> items;
    private List<String> price;
    private List<String> imageUrls;  // List<String> for image URLs
    private Context context;

    public PopularAdapter(List<String> items, List<String> price, List<String> imageUrls, Context context) {
        this.items = items;
        this.price = price;
        this.imageUrls = imageUrls;  // Accept List<String> for image URLs
        this.context = context;
    }

    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PopularItemBinding binding = PopularItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
        String item = items.get(position);
        String imageUrl = imageUrls.get(position);  // Get the image URL (String)
        String itemPrice = price.get(position);
        holder.bind(item, itemPrice, imageUrl);  // Pass the image URL to the bind method

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("MenuItemName", item);
            intent.putExtra("MenuItemImage", imageUrl);  // Pass the image URL instead of resource ID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        private PopularItemBinding binding;

        public PopularViewHolder(PopularItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, String price, String imageUrl) {
            binding.foodNamePopular.setText(item);
            binding.pricePopular.setText(price);

            // Use Glide to load the image from URL
            Glide.with(binding.imageView6.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)// Optional: Add a placeholder image
                    .into(binding.imageView6);  // Load the image into ImageView
        }
    }
}
