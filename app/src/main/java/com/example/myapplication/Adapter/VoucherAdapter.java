package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.Entity.Voucher;
import com.example.myapplication.Interface.IApplyVoucher;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class VoucherAdapter extends BaseAdapter {
    Context context;
    ArrayList<Voucher> vouchers;
    IApplyVoucher applyVoucher;
    public  VoucherAdapter(Context context, ArrayList<Voucher> items)
    {
        this.context =context;
        this.vouchers = items;
    }
    public  void setApplyVoucher(IApplyVoucher iApplyVoucher)
    {
        applyVoucher = iApplyVoucher;
    }


    @Override
    public int getCount() {
        return vouchers.size();
    }

    @Override
    public Object getItem(int i) {
        return vouchers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //set VIew
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.voucher_item, viewGroup, false);
        }
        //set info
        ImageView voucherColor;
        TextView discountText;
        TextView nameText;
        TextView voucherDescription;
        TextView expiryDate;
        Button applyButton;

        voucherColor = itemView.findViewById(R.id.voucherColor);
        discountText = itemView.findViewById(R.id.discountView);
        nameText = itemView.findViewById(R.id.voucherName);
        voucherDescription = itemView.findViewById(R.id.voucherDes);
        applyButton = itemView.findViewById(R.id.voucherApply);
        expiryDate = itemView.findViewById(R.id.expiryDate);
        Voucher voucher = vouchers.get(position);

        discountText.setText(String.valueOf(voucher.getFixedDiscount()) +"%");
        nameText.setText(voucher.getVoucherName());
        voucherDescription.setText(voucher.getVoucherDescription());
        expiryDate.setText(voucher.getExpiryDate().toString());

        //set apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyVoucher.ApplyVoucher(vouchers.get(position));
            }
        });
        //Select color for backColor
        int mod = new Random().nextInt(61)%8;
        int colorIndex = 0;
        Resources res = context.getResources();

        if(mod == 0)
        {
            colorIndex = R.color.l_Green;
        }
        else if (mod == 1)
        {
            colorIndex = R.color.l_Blue;
        }
        else if(mod == 2)
        {
            colorIndex = R.color.l_DarkBlue;
        }
        else if(mod == 3){
            colorIndex = R.color.l_Purple;
        }
        else if(mod == 4){
            colorIndex = R.color.d_Blue;
        }
        else if(mod == 5){
            colorIndex = R.color.d_DarkBlue;
        }
        else if(mod == 6){
            colorIndex = R.color.d_Green;
        }
        else if(mod == 7){
            colorIndex = R.color.d_Purple;
        }
        voucherColor.setColorFilter(res.getColor(colorIndex));

        return itemView;
    }

}
