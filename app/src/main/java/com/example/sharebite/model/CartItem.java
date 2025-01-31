package com.example.sharebite.model;

public class CartItem {
    private String foodName;
    private String foodPrice;
    private int foodImage;

    public CartItem(String foodName, String foodPrice, int foodImage) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public int getFoodImage() {
        return foodImage;
    }
}
