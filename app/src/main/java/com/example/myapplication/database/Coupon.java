package com.example.myapplication.database;

public class Coupon {
    private long id;
    private String code;
    private double amount;

    public Coupon(){}
    public Coupon(long id, String code, double amount) {
        this.id = id;
        this.code = code;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
