package com.example.myapplication.database;

public class PcComponent {
    private long id;
    private String componentName;
    private double price;

    public PcComponent() {}

    public PcComponent(long id, String componentName, double price) {
        this.id = id;
        this.componentName = componentName;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getComponentName() {
        return componentName;
    }
}
