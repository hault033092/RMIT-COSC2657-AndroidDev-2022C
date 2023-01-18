package com.example.myapplication.Entity;

import java.io.Serializable;
import java.util.Date;

public class Voucher implements Serializable {
    String voucherName;
    String voucherDescription;
    int fixedDiscount;
    String expiryDate;

    public Voucher(String voucherName, String voucherDescription, int fixedDiscount, String expiryDate) {
        this.voucherName = voucherName;
        this.voucherDescription = voucherDescription;
        this.fixedDiscount = fixedDiscount;
        this.expiryDate = expiryDate;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public int getFixedDiscount() {
        return fixedDiscount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
