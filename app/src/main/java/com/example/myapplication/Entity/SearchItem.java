package com.example.myapplication.Entity;

public class SearchItem {
    int image;
    String name;
    float price;

    public SearchItem(int image, String name, float price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getPriceAsString() {
        return "$" + getPrice();
    }
}
