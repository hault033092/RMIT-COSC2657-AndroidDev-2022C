package com.example.myapplication.database;

import java.io.Serializable;

public class PcComponent implements Serializable {
    private long id;
    private String name;
    private String specification;
    private String description;
    private String type;
    private int score;
    private double price;

    public PcComponent() {}

    public PcComponent(long id, String name, String specification, String description, String type, int score, double price) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.description = description;
        this.type = type;
        this.score = score;
        this.price = price;
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
}
