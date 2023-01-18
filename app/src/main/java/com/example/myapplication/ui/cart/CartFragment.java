package com.example.myapplication.ui.cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication.CheckOutActivity;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;


public class CartFragment extends Fragment {
    ArrayList<Item> itemList = new ArrayList<>();

    //define callback interface
    interface ICartCallBack{
        void onCartChange();
        void onCartDelete(Item cart,int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //generate data
//        GenerateTestingData();

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        //item count
//        String itemCount = itemList.size() + " items";
//        TextView itemCountView = view.findViewById(R.id.subHeader);
//        itemCountView.setText(itemCount);

        //recycle view
        RecyclerView recyclerView = view.findViewById(R.id.cartView);
        Cart_RecycleViewAdapter adapter = new Cart_RecycleViewAdapter(view.getContext(), itemList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        if(itemList.size() == 0)
        {
            DisplayEmptyCart(true);
        }

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
        TextView total = view.findViewById(R.id.total);

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
            public void onCartDelete(Item cart,int position) {
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
                //display empty cart if there are no item
                if(itemList.size() ==0)
                {
                    DisplayEmptyCart(true);
                }
            }
        });

        //Set Clear ALL
        TextView clear = view.findViewById(R.id.clear);
        clear.setOnClickListener(view1 -> {
            itemList.clear();
            adapter.notifyDataSetChanged();
            DisplayEmptyCart(true);

            subtotal.setText("$0");
            tax.setText("$0");
            total.setText("$0");
        });

        //Set CHECKOUT
        Button checkOut = view.findViewById(R.id.checkOut);
        checkOut.setOnClickListener(view12 -> {
            if(itemList.size() == 0) return;//reject if there are nothing in cart
            Intent intent =new Intent(getActivity(), CheckOutActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("items",itemList);
            intent.putExtra("Bundle",args);
            startActivity(intent);
        });

        return view;
    }

//    public void AddItemToCart()
//    {
//
//    }

    private void DisplayEmptyCart(boolean state) {
        FrameLayout layout = getView().findViewById(R.id.no_Item);

        if(state) {
            layout.setVisibility(View.VISIBLE);
        }
        else {
            layout.setVisibility(View.GONE);
        }
    }

    private void GenerateTestingData() {
        Item item = new Item(R.drawable.asset_microbit, "Microbit", "V1", 15.00f,1);
        Item item2 = new Item(R.drawable.asset_microbit, "Ram DDR", "4GB",10.00f, 2);
        Item item3 = new Item(R.drawable.asset_microbit, "Monitor RT", "Model 1572", 55.00f, 1);
        Item item4 = new Item(R.drawable.asset_microbit, "Razor Gaming mouse", "V2", 105.00f, 3);
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
    }

}