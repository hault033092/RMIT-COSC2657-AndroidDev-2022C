package com.example.myapplication.ui.pcbuilder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Entity.ComponentType;
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
        PCBuilderRecycleViewAdapter adapter = new PCBuilderRecycleViewAdapter(view.getContext(),types);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
