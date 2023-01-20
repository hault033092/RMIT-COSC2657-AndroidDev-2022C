package com.example.myapplication.ui.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication.Adapter.LocalItemsSingleton;
import com.example.myapplication.CheckOutActivity;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Interface.ILocalCartChange;
import com.example.myapplication.R;

import java.text.NumberFormat;

public class CartFragment extends Fragment implements ILocalCartChange {

    Cart_RecycleViewAdapter adapter;

    @Override
    public void notifyInsert(int size) {
        if(adapter == null) return;//no action can be taken
        adapter.notifyItemInserted(size-1);
    }

    //enable calling from singleton
    @Override
    public void notifyItemChange(int pos, int size) {
        if(adapter == null) return;//no action can be taken
        adapter.notifyItemRemoved(pos);
        adapter.notifyItemRangeChanged(pos,size);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void notifyClear() {
        if(adapter == null ) return;//should not be called
        adapter.notifyDataSetChanged();
        DisplayEmptyCart(true);
    }

    //define callback interface
    interface ICartCallBack{
        void onCartChange();
        void onCartDelete(Item cart,int position);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        int itemSize =LocalItemsSingleton.getInstance().getCartSize();
        //item count
        String itemCount = itemSize + " items";
        TextView itemCountView = view.findViewById(R.id.subHeader);
        itemCountView.setText(itemCount);

        //recycle view
        RecyclerView recyclerView = view.findViewById(R.id.cartView);
        adapter = new Cart_RecycleViewAdapter(view.getContext(), LocalItemsSingleton.getInstance().getCart());

        //recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        if(itemSize  == 0)
        {
            DisplayEmptyCart(true);
        }

        //calculation
        float subtotalCost = 0;
        for (int i = 0; i < itemSize; i++) {
            subtotalCost += LocalItemsSingleton.getInstance().getItem(i).getQuantityPrice();
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
                for (int i = 0; i < LocalItemsSingleton.getInstance().getCartSize(); i++) {
                    subtotalCost += LocalItemsSingleton.getInstance().getItem(i).getQuantityPrice();
                }
                float taxes = subtotalCost * 5 / 100;
                float totalCost = subtotalCost + taxes;

                subtotal.setText( fmt.format(subtotalCost));
                tax.setText(fmt.format(taxes));
                total.setText(fmt.format(totalCost));
            }

            @Override
            public void onCartDelete(Item cart,int position) {
                LocalItemsSingleton.getInstance().removeItem(position);
                //delete need to update price
                float subtotalCost = 0;
                for (int i = 0; i < LocalItemsSingleton.getInstance().getCartSize(); i++) {
                    subtotalCost += LocalItemsSingleton.getInstance().getItem(i).getQuantityPrice();
                }
                float taxes = subtotalCost * 5 / 100;
                float totalCost = subtotalCost + taxes;

                int itemSize =LocalItemsSingleton.getInstance().getCartSize();
                //item count
                String itemCount = itemSize + " items";
                itemCountView.setText(itemCount);

                subtotal.setText( fmt.format(subtotalCost));
                tax.setText(fmt.format(taxes));
                total.setText(fmt.format(totalCost));
                //display empty cart if there are no item
                if(LocalItemsSingleton.getInstance().getCartSize() ==0)
                {
                    DisplayEmptyCart(true);
                }
            }
        });

        //Set Clear ALL
        TextView clear = view.findViewById(R.id.clear);
        clear.setOnClickListener(view1 -> {
            LocalItemsSingleton.getInstance().clear();
            itemCountView.setText("0 items");
            subtotal.setText("$0.00");
            tax.setText("$0.00");
            total.setText("$0.00");
        });

        //Set CHECKOUT
        Button checkOut = view.findViewById(R.id.checkOut);
        checkOut.setOnClickListener(view12 -> {
            if(LocalItemsSingleton.getInstance().getCartSize()== 0) return;//reject if there are nothing in cart
            Intent intent =new Intent(getActivity(), CheckOutActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("items",LocalItemsSingleton.getInstance().getCart());
            intent.putExtra("Bundle",args);
            startActivity(intent);
        });

        return view;
    }

    private void DisplayEmptyCart(boolean state) {
         View v = getView();
         if(v == null) return; //some how happens

        FrameLayout layout = v.findViewById(R.id.no_Item);
        Log.d("check view","has this ever been called---------------------------");
        if(state) {
            Log.d("check view","hide the empty cart---------------------------");
            layout.setVisibility(View.VISIBLE);
        }
        else {
            layout.setVisibility(View.GONE);
        }
    }


}