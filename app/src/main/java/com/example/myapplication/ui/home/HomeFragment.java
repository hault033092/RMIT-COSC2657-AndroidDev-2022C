package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Adapter.HomeCateAdapter;
import com.example.myapplication.Adapter.HomeRecommendAdapter;
import com.example.myapplication.ContactUsActivity;
import com.example.myapplication.Entity.Category;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.ItemListActivity;
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
    List<Item> itemsList ;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        context = container.getContext();

        // Contact & About Buttons

        AppCompatButton contactUs = view.findViewById(R.id.contactUs);

        // Onclick events for buttons

        contactUs.setOnClickListener(view1 -> {
            Intent i = new Intent(context, ContactUsActivity.class);
            context.startActivity(i);
        });

        // Recycler view
        RecyclerView recyclerView = view.findViewById(R.id.cateView);

        ArrayList<Item> subList = new ArrayList<>() ;
        for(int i =0;i<4;i++)
        {
            subList.add(itemsList.get(i));
        }
        // Adapter
        HomeCateAdapter homeAdapter = new HomeCateAdapter(view.getContext(),cates);
        HomeRecommendAdapter recommendAdapter = new HomeRecommendAdapter(view.getContext(),subList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(homeAdapter);

        //Grid use 4 slots
        RecyclerView recommendView = view.findViewById(R.id.recommendList);
        recommendView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);

        recommendView.setLayoutManager(mLayoutManager);
        recommendView.setAdapter(recommendAdapter);

        // Add Show ALL BUTTON
        TextView showAll = view.findViewById(R.id.show_all);
        showAll.setOnClickListener(view1 -> {
            Intent i = new Intent(context, ItemListActivity.class);
            i.putExtra("passTAG","All");
            context.startActivity(i);
        });


        return view;
    }
    public void injectDatabase(Context c)
    {
        itemsList  = ItemsDatabase.retrieveAllSearchItems(c);
    }
}