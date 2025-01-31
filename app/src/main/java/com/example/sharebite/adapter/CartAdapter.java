package com.example.sharebite.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sharebite.databinding.CartItemBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<String> cartItems;
    private ArrayList<String> cartItemPrice;
    private ArrayList<Integer> cartImage;
    private int[] itemQuantities;

    public CartAdapter(ArrayList<String> cartItems, ArrayList<String> cartItemPrice, ArrayList<Integer> cartImage) {
        this.cartItems = cartItems;
        this.cartItemPrice = cartItemPrice;
        this.cartImage = cartImage;
        this.itemQuantities = new int[cartItems.size()];
        for (int i = 0; i < cartItems.size(); i++) {
            itemQuantities[i] = 1;
        }
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private CartItemBinding binding;

        public CartViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            int quantity = itemQuantities[position];
            binding.cartFoodName.setText(cartItems.get(position));
            binding.cartItemPrice.setText(cartItemPrice.get(position));
            binding.cartImage.setImageResource(cartImage.get(position));
            binding.cartItemQuantity.setText(String.valueOf(quantity));

            binding.minusButton.setOnClickListener(v -> decreaseQuantity(position));
            binding.plusButton.setOnClickListener(v -> increaseQuantity(position));
            binding.deleteButton.setOnClickListener(v -> {
                int itemPosition = getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    deleteItem(itemPosition);
                }
            });
        }

        private void increaseQuantity(int position) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++;
                binding.cartItemQuantity.setText(String.valueOf(itemQuantities[position]));
            }
        }

        private void decreaseQuantity(int position) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--;
                binding.cartItemQuantity.setText(String.valueOf(itemQuantities[position]));
            }
        }

        private void deleteItem(int position) {
            cartItems.remove(position);
            cartImage.remove(position);
            cartItemPrice.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
        }
    }
}