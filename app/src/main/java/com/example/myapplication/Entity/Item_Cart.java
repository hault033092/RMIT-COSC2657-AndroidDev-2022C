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

    //setter

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    //getter
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }
    public  float getQuantityPrice()
    {return price * amount;}
    public String getPriceString()
    {
        return "$" + String.valueOf(price);
    }

    public int getAmount() {
        return amount;
    }
}
