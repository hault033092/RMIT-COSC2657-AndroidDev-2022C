package com.example.myapplication.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Adapter.HomeCateAdapter;
import com.example.myapplication.Entity.Category;
import com.example.myapplication.R;

public class HomeFragment extends Fragment {
    Category[] cates = {
            new Category(R.drawable.ic_baseline_auto_awesome_mosaic_24,"All"),
            new Category(R.drawable.mainboard_icon,"Mainboard"),
            new Category(R.drawable.cpu_icon,"CPU"),
            new Category(R.drawable.ram_icon,"RAM"),
            new Category(R.drawable.vga_icon,"VGA"),
            new Category(R.drawable.storagedrive_icon,"Storage Drive")
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_home,container,false);
        //recycle view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cateView);

        //Adapter
        HomeCateAdapter homeAdapter = new HomeCateAdapter(view.getContext(),cates);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(homeAdapter);

        return view;
    }
}