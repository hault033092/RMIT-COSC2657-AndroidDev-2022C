package com.example.myapplication.ui.pcbuilder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Entity.ComponentType;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pcbuilder,container,false);
        //recycle view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.typeList);
        InjectItem();
        PCBuilderRecycleViewAdapter adapter = new PCBuilderRecycleViewAdapter(view.getContext(),types);

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

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
