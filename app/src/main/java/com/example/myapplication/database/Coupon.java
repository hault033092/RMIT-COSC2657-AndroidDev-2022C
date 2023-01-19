package com.example.myapplication.database;

public class Coupon {
    private long id;
    private String name;
    private String description;
    private String expiryDate;
    private int fixedDiscount;

    public Coupon(){}
    public Coupon(long id, String name, String description, String expiryDate, int fixedDiscount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expiryDate = expiryDate;
        this.fixedDiscount = fixedDiscount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFixedDiscount() {
        return fixedDiscount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
