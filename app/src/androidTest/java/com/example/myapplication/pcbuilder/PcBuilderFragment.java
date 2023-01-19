package com.example.myapplication.pcbuilder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.CheckOutActivity;
import com.example.myapplication.Entity.ComponentType;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Interface.IPCTypeChange;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class PcBuilderFragment extends Fragment {
    ComponentType[] types = {
            new ComponentType("Mainboard","Connect components"),
            new ComponentType("CPU","Cental Processing Unit"),
            new ComponentType("RAM","Random Access Unit"),
            new ComponentType("Storage Drive","Data Storage Device"),
            new ComponentType("Power Supply", "Convert Electric Current for Computer"),
            new ComponentType("VGA","Graphic Processing Unit"),
            new ComponentType("Monitor","Display Device")
    };

    float subtotal = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pcbuilder,container,false);
        //recycle view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.typeList);
        TextView subtotalView = view.findViewById(R.id.total);

        //generate sample data
        InjectItem();
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        //create adapter and setting
        PCBuilderRecycleViewAdapter adapter = new PCBuilderRecycleViewAdapter(view.getContext(),types);
        adapter.setPcTypeChange(new IPCTypeChange() {
            @Override
            public void onCheckChange(int typePosition, int itemPosition,boolean state) {
                //fetch item
                Item item = types[typePosition].getItem(itemPosition);
                if(state)
                {
                    //add this item to cost
                    subtotal += item.getQuantityPrice();
                }
                else{
                    subtotal -= item.getQuantityPrice();
                }
                //display
                subtotalView.setText(fmt.format(subtotal));
            }

            @Override
            public void onItemRemove(int itemPosition, float price) {
                subtotal -= price;
                subtotalView.setText(fmt.format(subtotal));
            }
        });

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        //compute subtotal
        subtotal = 0;
        for(int i=0;i<types.length;i++)
        {
            subtotal += types[i].getCheckPrice();
        }
        subtotalView.setText(fmt.format(subtotal));

        //set CHECKOUT
        Button checkOut = view.findViewById(R.id.checkOut);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Item> savedItems = new ArrayList<>();
                //get the list of selected
                for(int i=0;i< types.length;i++)
                {
                    ArrayList<Item> items = types[i].getItems();
                    for(int j=0;j<items.size();j++)
                    {
                        if(!types[i].getChecked(j)) continue;
                        //this item is checked please add this one to the check out list
                        savedItems.add(items.get(j));
                    }
                }
                //Reject if there are no item to add
                if(savedItems.size() == 0) return;

                Intent intent =new Intent(getActivity(), CheckOutActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("items",savedItems);
                intent.putExtra("Bundle",args);
                startActivity(intent);
            }
        });
        return view;
    }

    private void InjectItem()
    {
        Item item = new Item(R.drawable.asset_microbit, "Microbit", "V1", 15.00f,1);
        Item item2 = new Item(R.drawable.asset_microbit, "Ram DDR", "4GB",10.00f, 2);
        Item item3 = new Item(R.drawable.asset_microbit, "Monitor RT", "Model 1572", 55.00f, 1);
        Item item4 = new Item(R.drawable.asset_microbit, "Razor Gaming mouse", "V2", 105.00f, 1);
        Item item5 = new Item(R.drawable.asset_microbit, "Ledendary keyboard", "V2", 15.00f, 1);
        Item item6 = new Item(R.drawable.asset_microbit, " Mini Nuclear power house", "V2", 399.00f, 1);
        types[0].addComponent(item);
        types[1].addComponent(item2);
        types[0].addComponent(item3);
        types[4].addComponent(item5);
        types[4].addComponent(item4);
        types[4].addComponent(item6);
    }
}
