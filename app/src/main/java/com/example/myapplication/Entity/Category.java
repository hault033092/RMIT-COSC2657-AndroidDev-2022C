package com.example.myapplication.Entity;

public class Category {
    int image;
    String name;

    public Category(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
