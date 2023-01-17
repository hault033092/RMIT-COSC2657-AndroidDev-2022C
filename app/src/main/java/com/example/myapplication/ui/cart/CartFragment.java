package com.example.myapplication.ui.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Entity.Item_Cart;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;


public class CartFragment extends Fragment {
    ArrayList<Item_Cart> itemList = new ArrayList<Item_Cart>();

    //define callback interface
    interface ICartCallBack{
        void onCartChange();
        void onCartDelete(Item_Cart cart,int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //generate data
        GenerateTesingData();

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        //item count
        String itemCount = itemList.size() + " items";
        TextView itemCountView = view.findViewById(R.id.subHeader);
        itemCountView.setText(itemCount);

        //recycle view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cartView);
        Cart_RecycleViewAdapter adapter = new Cart_RecycleViewAdapter(view.getContext(), itemList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        //calculation
        float subtotalCost = 0;
        for (int i = 0; i < itemList.size(); i++) {
            subtotalCost += itemList.get(i).getQuantityPrice();
        }
        float taxes = subtotalCost * 5 / 100;
        float totalCost = subtotalCost + taxes;

        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        TextView subtotal = view.findViewById(R.id.cart_subtotalCost);
        TextView tax = view.findViewById(R.id.cart_tax);
        TextView total = view.findViewById(R.id.cart_totalCost);
        //set starting value
        subtotal.setText(fmt.format(subtotalCost));
        tax.setText( fmt.format(taxes));
        total.setText(fmt.format(totalCost));
        //set updating value
        adapter.setCartChangeListener(new ICartCallBack() {
            @Override
            public void onCartChange() {
                float subtotalCost = 0;
                for (int i = 0; i < itemList.size(); i++) {
                    subtotalCost += itemList.get(i).getQuantityPrice();
                }
                float taxes = subtotalCost * 5 / 100;
                float totalCost = subtotalCost + taxes;

                subtotal.setText( fmt.format(subtotalCost));
                tax.setText(fmt.format(taxes));
                total.setText(fmt.format(totalCost));
            }

            @Override
            public void onCartDelete(Item_Cart cart,int position) {
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position,itemList.size());
                itemList.remove(position);
                //delete need to update price
                float subtotalCost = 0;
                for (int i = 0; i < itemList.size(); i++) {
                    subtotalCost += itemList.get(i).getQuantityPrice();
                }
                float taxes = subtotalCost * 5 / 100;
                float totalCost = subtotalCost + taxes;

                subtotal.setText( fmt.format(subtotalCost));
                tax.setText(fmt.format(taxes));
                total.setText(fmt.format(totalCost));
            }
        });

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