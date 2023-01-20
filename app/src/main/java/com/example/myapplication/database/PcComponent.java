package com.example.myapplication.database;

import java.io.Serializable;

public class PcComponent implements Serializable {
    private long id;
    private int image;
    private String name;
    private String specification;
    private String description;
    private String type;
    private int score;
    private double price;

    public PcComponent() {}

    public PcComponent(long id, String name, int image, String specification, String description, String type, int score, double price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.specification = specification;
        this.description = description;
        this.type = type;
        this.score = score;
        this.price = price;
    }

    public PcComponent(String s) {
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSpecification() {
        return specification;
    }
    
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getScore() {
        return score;
    }

    public String getType() {
        return type;
    }
}
