package com.example.myapplication.Entity;
import android.content.Context;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.database.PcComponent;

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

    public Item(PcComponent comp,Context context)
    {

        //this.image = context.getResources().getIdentifier(comp.getImage(), "drawable", context.getPackageName());
        this.name =comp.getName();
        this.specification= comp.getSpecification();
        this.description = comp.getDescription();
        this.type = comp.getType();
        this.score= comp.getScore();
        this.price = (float)comp.getPrice();
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

    public int getTypeIndex()
    {
        if(type == "Mainboard")
        {
            return 0;
        }
        else if(type == "CPU")
        {
            return 1;
        }
        else if(type == "RAM")
        {
            return 2;
        }
        else if(type == "Storage Drive")
        {
            return 3;
        }
        else if(type == "Power Supply")
        {
            return 4;
        }
        else if(type == "VGA")
        {
            return 5;
        }
        else
        {
            return 6;
        }
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

    @Override
    public String toString() {
        return "Item{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", score=" + score +
                ", specification='" + specification + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
