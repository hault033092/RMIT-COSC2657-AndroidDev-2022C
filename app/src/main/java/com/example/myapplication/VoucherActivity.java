package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.myapplication.Adapter.VoucherAdapter;
import com.example.myapplication.Entity.Voucher;
import com.example.myapplication.Interface.IApplyVoucher;

import java.util.ArrayList;
import java.util.Objects;

public class VoucherActivity extends AppCompatActivity implements IApplyVoucher {

    ArrayList<Voucher> vouchers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        GenerateData();

        //return button
        FrameLayout returnBut = findViewById(R.id.ReturnButton);
        returnBut.setOnClickListener(view -> finish());

        GridView grid = findViewById(R.id.grid);
        VoucherAdapter adapter = new VoucherAdapter(this,vouchers);
        adapter.setApplyVoucher(this);
        grid.setAdapter(adapter);
    }

    private  void GenerateData()
    {

        Voucher v1 = new Voucher("Khoa Special 10","discount purchasement by 10%", 10, "18/02/2023");
        Voucher v2 = new Voucher("Khoa Special 20","discount purchasement by 5%", 5, "21/03/2023");
        Voucher v3 = new Voucher("Khoa Special 30","discount purchasement by 20%", 30, "20/02/2023");
    vouchers.add(v1);
    vouchers.add(v2);
    vouchers.add(v3);
    }

    @Override
    public void ApplyVoucher(Voucher voucher) {
        Intent intent = new Intent();
        intent.putExtra("voucher",voucher);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}