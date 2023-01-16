package com.example.myapplication.Entity;

import java.io.Serializable;

public class Item_Cart implements Serializable {
    String name;

    int image;
    float price;
    int amount;

    public Item_Cart(String name, int image, float price, int amount) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }
    public String getPriceString()
    {
        return "$" + String.valueOf(price);
    }

    public int getAmount() {
        return amount;
    }
}
