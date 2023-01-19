package com.example.myapplication.Entity;

import java.io.Serializable;

public class Item implements Serializable {
    int image;
    String name;
    String type;
    float score;
    String specification;
    String description;
    float price;
    int amount;

    public String getType() {
        return type;
    }

    // Constructor for ItemDetail Activity
    public Item(int image, String name, String specification, String description, float price, int amount) {
        this.image = image;
        this.name = name;
        this.specification = specification;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    // Constructor for Cart Fragment
    public Item(int image, String name, String specification, float price, int amount) {
        this.image = image;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.amount = amount;
    }

    // Constructor for Search Fragment
    public Item(int image, String name, String specification, String description, float price) {
        this.image = image;
        this.name = name;
        this.specification = specification;
        this.description = description;
        this.price = price;
    }
    public Item setType(String type)
    {
        this.type = type;
        return this;
    }
    public Item setScore(int s){
        this.score= s;
        return this;
    }

    //setters
    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    //getters
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;

    }

    public String getSpecification() { return specification; }

    public String getDescription() { return description; }

    public float getPrice() {
        return price;
    }

    public float getQuantityPrice()
    {return price * amount;}

    public String getPriceAsString()
    {
        return "$" + price;
    }

    public int getAmount() {
        return amount;
    }

    public String getAmountAsString() { return String.valueOf(amount); }
}
