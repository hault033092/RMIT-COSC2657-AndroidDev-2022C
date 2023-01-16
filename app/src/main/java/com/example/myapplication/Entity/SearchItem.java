package com.example.myapplication.Entity;

public class SearchItem {
    int image;
    String name;
    String specification;
    float price;

    public SearchItem(int image, String name, String specification, float price) {
        this.image = image;
        this.name = name;
        this.specification = specification;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSpecification() { return specification; }

    public float getPrice() {
        return price;
    }

    public String getPriceAsString() {
        return "$" + getPrice();
    }
}
