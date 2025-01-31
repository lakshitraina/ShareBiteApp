package com.example.sharebite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.sharebite.activity.DetailsActivity;
import com.example.sharebite.databinding.MenuItemBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<String> menuItemsName;
    private List<String> menuItemPrice;
    private List<Integer> menuImage;
    private Context context;

    public MenuAdapter(List<String> menuItemsName, List<String> menuItemPrice, List<Integer> menuImage, Context context) {
        this.menuItemsName = menuItemsName;
        this.menuItemPrice = menuItemPrice;
        this.menuImage = menuImage;
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return menuItemsName.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private MenuItemBinding binding;

        public MenuViewHolder(MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Set on click listener to open details
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("MenuItemName", menuItemsName.get(position));
                    intent.putExtra("MenuItemImage", menuImage.get(position));
                    context.startActivity(intent);
                }
            });
        }

        public void bind(int position) {
            binding.menufoodName.setText(menuItemsName.get(position));
            binding.menuPrice.setText(menuItemPrice.get(position));
            binding.menuImage.setImageResource(menuImage.get(position));
        }
    }
}
