package com.example.myapplication.ui.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Entity.Item_Cart;
import com.example.myapplication.R;

import java.util.ArrayList;


public class CartFragment extends Fragment{
    ArrayList<Item_Cart> itemList = new ArrayList<Item_Cart>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        //generate data
        GenerateTesingData();

        View view =inflater.inflate(R.layout.fragment_cart,container,false);

        //item count
        String itemCount = itemList.size() + " items";
        TextView itemCountView = view.findViewById(R.id.itemCountView);
        itemCountView.setText(itemCount);

        //recycle view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cartView);
        Cart_RecycleViewAdapter adapter = new Cart_RecycleViewAdapter(view.getContext(),itemList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        //Cost
        //calculation
        float subtotalCost =0;
        for(int i=0;i<itemList.size();i++)
        {
            subtotalCost += itemList.get(i).getPrice();
        }
        float taxes = subtotalCost * 5/100;
        float totalCost = subtotalCost + taxes;

        TextView subtotal = view.findViewById(R.id.cart_subtotalCost);
        TextView tax = view.findViewById(R.id.cart_tax);
        TextView total = view.findViewById(R.id.cart_totalCost);

        subtotal.setText( "$" + String.valueOf(subtotalCost));
        tax.setText("$" + String.valueOf(taxes));
        total.setText(String.valueOf(totalCost));
        return view;
    }
    private void GenerateTesingData()
    {
        Item_Cart item = new Item_Cart("Microbit",R.drawable.asset_microbit,15.00f,1);
        Item_Cart item2 = new Item_Cart("Ram DDR 4GB",R.drawable.asset_microbit,10.00f,2);
        Item_Cart item3 = new Item_Cart("Monitor RT Model 1572",R.drawable.asset_microbit,55.00f,1);
        Item_Cart item4 = new Item_Cart("Gaming mouse",R.drawable.asset_microbit,105.00f,3);
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
    }

}