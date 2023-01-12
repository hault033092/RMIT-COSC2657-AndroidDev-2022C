package com.example.myapplication.database;

public class PcComponent {
    private String id;
    private String componentName;
    private long price;

    public PcComponent() {}

    public PcComponent(String id, String componentName, long price) {
        this.id = id;
        this.componentName = componentName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    public String getComponentName() {
        return componentName;
    }
}
