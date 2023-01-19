package com.example.myapplication.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication.Adapter.HomeCateAdapter;
import com.example.myapplication.Adapter.SearchAdapter;
import com.example.myapplication.Entity.Category;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Model.ItemsDatabase;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Category[] cates = {
            new Category(R.drawable.ic_baseline_auto_awesome_mosaic_24,"All"),
            new Category(R.drawable.mainboard_icon,"Mainboard"),
            new Category(R.drawable.cpu_icon,"CPU"),
            new Category(R.drawable.ram_icon,"RAM"),
            new Category(R.drawable.vga_icon,"VGA"),
            new Category(R.drawable.storagedrive_icon,"Storage Drive")
    };
    List<Item> itemsList = ItemsDatabase.retrieveAllSearchItems();
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

        ArrayList<Item> subList = new ArrayList<>() ;
        for(int i =0;i<4;i++)
        {
            subList.add(itemsList.get(i));
        }
        //Grid use 4 slots
        GridView gridView = view.findViewById(R.id.itemsGrid);
        SearchAdapter searchAdapter = new SearchAdapter(subList, getContext());
        gridView.setAdapter(searchAdapter);


        return view;
    }
}